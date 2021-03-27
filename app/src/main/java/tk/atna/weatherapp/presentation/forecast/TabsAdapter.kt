package tk.atna.weatherapp.presentation.forecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import tk.atna.weatherapp.presentation.forecast.hottest.HottestFragment
import tk.atna.weatherapp.presentation.forecast.upcoming.UpcomingFragment

class TabsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = TAB_COUNT

    override fun getItemId(position: Int) = position.toLong()

    override fun containsItem(itemId: Long) = true

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UpcomingFragment()
            else -> HottestFragment()
        }
    }

    companion object {
        const val TAB_COUNT = 2
    }
}