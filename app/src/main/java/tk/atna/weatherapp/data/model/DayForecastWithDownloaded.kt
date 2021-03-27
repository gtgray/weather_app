package tk.atna.weatherapp.data.model

import androidx.room.Embedded
import androidx.room.Relation
import tk.atna.weatherapp.domain.model.DayForecast

data class DayForecastWithDownloaded(
    @Embedded
    val dayForecastEntity: DayForecastEntity,
    @Relation(
        parentColumn = "day",
        entityColumn = "day"
    )
    val downloadedEntity: DownloadedEntity?
)

fun List<DayForecastWithDownloaded>.toModel() = map { it.toModel() }

fun DayForecastWithDownloaded.toModel() = DayForecast(
    day = dayForecastEntity.day,
    description = dayForecastEntity.description,
    sunrise = dayForecastEntity.sunrise,
    sunset = dayForecastEntity.sunset,
    chanceRain = dayForecastEntity.chanceRain,
    high = dayForecastEntity.high,
    low = dayForecastEntity.low,
    image = dayForecastEntity.image,
    downloaded = downloadedEntity?.downloaded ?: false
)