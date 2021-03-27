package tk.atna.weatherapp.presentation.forecast.hottest

import androidx.lifecycle.asLiveData
import tk.atna.weatherapp.domain.interactor.ForecastInteractor
import tk.atna.weatherapp.presentation.base.BaseViewModel

class HottestViewModel(
    forecastInteractor: ForecastInteractor
) : BaseViewModel() {

    val hottest = forecastInteractor.getHottestForecast().asLiveData(coroutineContext)
}