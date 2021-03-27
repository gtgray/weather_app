package tk.atna.weatherapp.presentation.forecast.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tk.atna.weatherapp.databinding.FragmentUpcomingBinding
import tk.atna.weatherapp.domain.model.DayForecast
import tk.atna.weatherapp.presentation.forecast.adapter.ForecastAdapter
import tk.atna.weatherapp.stuff.openDetailsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private val binding by lazy {
        FragmentUpcomingBinding.inflate(layoutInflater)
    }

    private val viewModel: UpcomingViewModel by viewModel()

    private val adapter by lazy {
        ForecastAdapter(requireContext(), ::openDetails)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.upcoming.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun openDetails(day: DayForecast) {
        requireContext().openDetailsScreen(day.day)
    }
}