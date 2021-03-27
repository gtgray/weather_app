package tk.atna.weatherapp.data.repository

import tk.atna.weatherapp.data.dadasource.api.ServerApi
import tk.atna.weatherapp.data.dadasource.db.ForecastDao
import tk.atna.weatherapp.data.model.DownloadedEntity
import tk.atna.weatherapp.data.model.toEntity
import tk.atna.weatherapp.data.model.toModel
import tk.atna.weatherapp.domain.model.DayForecast
import tk.atna.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ForecastRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val serverApi: ServerApi
) : ForecastRepository {

    override suspend fun loadForecast() {
        serverApi.loadForecast()
            .map { it.toEntity() }
            .also { forecastDao.insert(it) }
    }

    override fun getUpcomingForecast(): Flow<List<DayForecast>> {
        return forecastDao.getUpcoming().map { it.toModel() }
    }

    override fun getHottestForecast(): Flow<List<DayForecast>> {
        return forecastDao.getHottest(RAIN_CHANCE_LIMIT).map { it.toModel() }
    }

    override fun getDayForecast(day: Int): Flow<DayForecast> {
        return forecastDao.getDayForecast(day).map { it.toModel() }
    }

    override suspend fun updateImageDownloaded(day: Int) {
        forecastDao.insert(DownloadedEntity(day, true))
    }

    companion object {
        const val RAIN_CHANCE_LIMIT = 0.5f
    }
}