package tk.atna.weatherapp.data.dadasource.db

import androidx.room.*
import tk.atna.weatherapp.data.model.DayForecastEntity
import tk.atna.weatherapp.data.model.DayForecastWithDownloaded
import tk.atna.weatherapp.data.model.DownloadedEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ForecastDao {

    @Query("SELECT * FROM forecast WHERE day = :day")
    abstract fun getDayForecast(day: Int): Flow<DayForecastWithDownloaded>

    @Query("SELECT * FROM forecast ORDER BY day ASC")
    abstract fun getUpcoming(): Flow<List<DayForecastWithDownloaded>>

    @Query("SELECT * FROM forecast WHERE chanceRain < :limit ORDER BY high DESC")
    abstract fun getHottest(limit: Float): Flow<List<DayForecastWithDownloaded>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(downloaded: DownloadedEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(dayForecast: DayForecastEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(days: List<DayForecastEntity>): List<Long>
}