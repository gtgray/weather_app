package tk.atna.weatherapp.presentation.forecast.upcoming

import androidx.lifecycle.asLiveData
import tk.atna.weatherapp.domain.interactor.ForecastInteractor
import tk.atna.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val forecastInteractor: ForecastInteractor
) : BaseViewModel() {

    val upcoming = forecastInteractor.getUpcomingForecast().asLiveData(coroutineContext)

    init {
        launch {
            forecastInteractor.loadForecast()
        }
    }
}