package tk.atna.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class DayForecastEntity(
    @PrimaryKey
    val day: Int,
    val description: String,
    val sunrise: Long,
    val sunset: Long,
    val chanceRain: Float,
    val high: Int,
    val low: Int,
    val image: String
)
