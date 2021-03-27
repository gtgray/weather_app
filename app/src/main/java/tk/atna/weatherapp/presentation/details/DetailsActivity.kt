package tk.atna.weatherapp.presentation.details

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import tk.atna.weatherapp.R
import tk.atna.weatherapp.databinding.ActivityDetailsBinding
import tk.atna.weatherapp.extension.load
import tk.atna.weatherapp.extension.toSimpleTime
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: DetailsViewModel by viewModel(
        parameters = { parametersOf(day) }
    )

    private var day: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        processArgs(intent)
        // no day provided - go back
        if (day == null) return finish()

        // usual way
        setTitle()
        displayForecast()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun processArgs(intent: Intent) {
        intent.extras?.get(DETAILS_DAY)?.let { day = it as Int }
    }

    private fun setTitle() {
        title = getString(R.string.day_number, day)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun displayForecast() {
        with(binding) {
            viewModel.dayForecast.observe(this@DetailsActivity) { forecast ->
                desc.text = forecast.description
                chanceRain.text = String.format(
                    getString(R.string.details_rain_chance),
                    forecast.chanceRainPercent
                )
                temperature.text = String.format(
                    getString(R.string.details_temperature_range),
                    forecast.high,
                    forecast.low
                )
                sunrise.text = String.format(
                    getString(R.string.details_sunrise),
                    forecast.sunriseMillis.toSimpleTime()
                )
                sunset.text = String.format(
                    getString(R.string.details_sunset),
                    forecast.sunsetMillis.toSimpleTime()
                )
                // deal with image
                if (forecast.downloaded) {
                    image.load(forecast.image)
                    download.isVisible = false
                } else {
                    image.setBackgroundResource(R.drawable.ic_launcher_background)
                    download.isVisible = true
                    download.setOnClickListener {
                        viewModel.loadImage(forecast)
                    }
                }
            }
        }
    }

    companion object {
        const val DETAILS_DAY = "details_day"
    }
}
