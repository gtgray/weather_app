package tk.atna.weatherapp.presentation.details

import androidx.lifecycle.asLiveData
import tk.atna.weatherapp.domain.interactor.ForecastInteractor
import tk.atna.weatherapp.domain.model.DayForecast
import tk.atna.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    day: Int,
    private val forecastInteractor: ForecastInteractor
) : BaseViewModel() {

    val dayForecast = forecastInteractor.getDayForecast(day).asLiveData(coroutineContext)

    fun loadImage(dayForecast: DayForecast) {
        launch {
            forecastInteractor.loadImage(dayForecast)
        }
    }
}