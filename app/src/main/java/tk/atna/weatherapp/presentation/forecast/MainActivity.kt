package tk.atna.weatherapp.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tk.atna.weatherapp.R
import tk.atna.weatherapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val pagerAdapter = TabsAdapter(supportFragmentManager, lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTitle(R.string.forecast)

        with (binding) {
            pager.run {
                adapter = pagerAdapter
                isSaveEnabled = false
            }

            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.setText(
                    when (position) {
                        0 -> R.string.upcoming
                        else -> R.string.hottest
                    }
                )
            }.attach()
        }
    }
}
