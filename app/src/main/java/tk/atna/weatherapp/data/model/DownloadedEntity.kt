package tk.atna.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tk.atna.weatherapp.domain.model.DayForecast

@Entity(tableName = "downloaded")
data class DownloadedEntity(
    @PrimaryKey
    val day: Int,
    val downloaded: Boolean? = null
)