package tk.atna.weatherapp.data.dadasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.atna.weatherapp.data.model.DayForecastEntity
import tk.atna.weatherapp.data.model.DownloadedEntity

@Database(
    entities = [
        DayForecastEntity::class,
        DownloadedEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}