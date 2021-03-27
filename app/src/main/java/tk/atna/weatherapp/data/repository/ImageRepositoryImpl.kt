package tk.atna.weatherapp.data.repository

import android.content.Context
import tk.atna.weatherapp.domain.repository.ImageRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ImageRepositoryImpl(
    private val context: Context
) : ImageRepository {

    override suspend fun loadImage(url: String): File {
        return suspendCancellableCoroutine {
            Glide.with(context)
                .download(url)
                .addListener(object : RequestListener<File> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<File>,
                        isFirstResource: Boolean
                    ): Boolean {
                        it.resumeWithException(e?.rootCauses?.firstOrNull() ?: Exception("Unknown exception"))
                        return false
                    }

                    override fun onResourceReady(
                        resource: File,
                        model: Any,
                        target: Target<File>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        it.resume(resource)
                        return false
                    }
                })
                .preload(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        }
    }
}