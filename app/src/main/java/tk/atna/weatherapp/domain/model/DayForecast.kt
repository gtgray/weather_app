package tk.atna.weatherapp.domain.model

data class DayForecast(
    val day: Int,
    val description: String,
    val sunrise: Long,
    val sunset: Long,
    val chanceRain: Float,
    val high: Int,
    val low: Int,
    val image: String,
    val downloaded: Boolean
) {

    val chanceRainPercent: Int
        get() = (chanceRain * 100).toInt()

    val sunriseMillis: Long
        get() = sunrise * 1000

    val sunsetMillis: Long
        get() = sunset * 1000
}