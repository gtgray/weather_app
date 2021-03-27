package tk.atna.weatherapp.domain.interactor

import tk.atna.weatherapp.domain.model.DayForecast
import tk.atna.weatherapp.domain.repository.ForecastRepository
import tk.atna.weatherapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ForecastInteractor(
    private val forecastRepository: ForecastRepository,
    private val imageRepository: ImageRepository
) {

    suspend fun loadForecast() {
        forecastRepository.loadForecast()
    }

    fun getUpcomingForecast(): Flow<List<DayForecast>> {
        return forecastRepository.getUpcomingForecast()
    }

    fun getHottestForecast(): Flow<List<DayForecast>> {
        return forecastRepository.getHottestForecast()
    }

    fun getDayForecast(day: Int): Flow<DayForecast> {
        return forecastRepository.getDayForecast(day)
    }

    suspend fun loadImage(dayForecast: DayForecast) {
        imageRepository.loadImage(dayForecast.image)
        forecastRepository.updateImageDownloaded(dayForecast.day)
    }
}