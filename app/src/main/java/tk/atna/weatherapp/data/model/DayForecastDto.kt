package tk.atna.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class DayForecastDto(
    @SerializedName("day")
    val day: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("chance_rain")
    val chanceRain: Float,
    @SerializedName("high")
    val high: Int,
    @SerializedName("low")
    val low: Int,
    @SerializedName("image")
    val image: String
)

fun DayForecastDto.toEntity() = DayForecastEntity(
    day = day,
    description = description,
    sunrise = sunrise,
    sunset = sunset,
    chanceRain = chanceRain,
    high = high,
    low = low,
    image = image
)