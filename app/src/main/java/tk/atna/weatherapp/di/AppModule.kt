package tk.atna.weatherapp.di

import androidx.room.Room
import tk.atna.weatherapp.data.dadasource.api.ServerApi
import tk.atna.weatherapp.data.dadasource.db.AppDatabase
import tk.atna.weatherapp.data.repository.ForecastRepositoryImpl
import tk.atna.weatherapp.data.repository.ImageRepositoryImpl
import tk.atna.weatherapp.domain.interactor.ForecastInteractor
import tk.atna.weatherapp.domain.repository.ForecastRepository
import tk.atna.weatherapp.domain.repository.ImageRepository
import tk.atna.weatherapp.presentation.details.DetailsViewModel
import tk.atna.weatherapp.presentation.forecast.hottest.HottestViewModel
import tk.atna.weatherapp.presentation.forecast.upcoming.UpcomingViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // data base init
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "forecast_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().forecastDao() }

    // network init
    single { Gson() }
    single<Converter.Factory> { GsonConverterFactory.create(get()) }
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://5c5c8ba58d018a0014aa1b24.mockapi.io")
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single<ServerApi> { get<Retrofit>().create(ServerApi::class.java) }

    // other stuff
    single<ForecastRepository> { ForecastRepositoryImpl(get(), get()) }
    single<ImageRepository> { ImageRepositoryImpl(get()) }

    factory { ForecastInteractor(get(), get()) }

    viewModel { UpcomingViewModel(get()) }
    viewModel { HottestViewModel(get()) }
    viewModel { (day: Int) -> DetailsViewModel(day, get()) }
}
