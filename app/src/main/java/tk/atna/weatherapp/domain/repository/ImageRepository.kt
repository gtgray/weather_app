package tk.atna.weatherapp.domain.repository

import java.io.File

interface ImageRepository {
    suspend fun loadImage(url: String): File
}