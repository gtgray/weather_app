package tk.atna.weatherapp.data.dadasource.api

import tk.atna.weatherapp.data.model.DayForecastDto
import retrofit2.http.GET

interface ServerApi {

    @GET("api/forecast")
    suspend fun loadForecast(): List<DayForecastDto>
}