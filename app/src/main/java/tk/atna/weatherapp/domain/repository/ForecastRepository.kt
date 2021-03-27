package tk.atna.weatherapp.domain.repository

import tk.atna.weatherapp.domain.model.DayForecast
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    suspend fun loadForecast()
    fun getUpcomingForecast(): Flow<List<DayForecast>>
    fun getHottestForecast(): Flow<List<DayForecast>>
    fun getDayForecast(day: Int): Flow<DayForecast>
    suspend fun updateImageDownloaded(day: Int)
}