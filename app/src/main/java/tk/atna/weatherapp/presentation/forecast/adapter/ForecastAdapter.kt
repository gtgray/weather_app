package tk.atna.weatherapp.presentation.forecast.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView;
import tk.atna.weatherapp.R
import tk.atna.weatherapp.databinding.ItemHottestBinding
import tk.atna.weatherapp.domain.model.DayForecast
import tk.atna.weatherapp.extension.load

class ForecastAdapter(
    context: Context,
    private val onItemClick: ((DayForecast) -> Unit)
) : ListAdapter<DayForecast, ForecastAdapter.ItemViewHolder>(ItemDiffCallback()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflater, parent, onItemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ItemViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onItemClick: ((DayForecast) -> Unit),
        private val binding: ItemHottestBinding = ItemHottestBinding.inflate(inflater, parent, false)
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: DayForecast

        init {
            binding.root.setOnClickListener { onItemClick.invoke(item) }
        }

        fun bind(newItem: DayForecast) {
            item = newItem
            with(binding) {
                day.text = String.format(
                    itemView.context.getString(R.string.item_day),
                    item.day,
                    item.description
                )
                chanceRain.text = String.format(
                    itemView.context.getString(R.string.item_rain_chance),
                    item.chanceRainPercent
                )
                temperature.text = String.format(
                    itemView.context.getString(R.string.item_temperature_range),
                    item.high,
                    item.low
                )

                if (item.downloaded) {
                    image.load(item.image)
                } else {
                    image.setBackgroundResource(R.drawable.ic_launcher_background)
                }
            }
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<DayForecast>() {

        override fun areItemsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
            return oldItem.day == newItem.day
        }

        override fun areContentsTheSame(oldItem: DayForecast, newItem: DayForecast): Boolean {
            return oldItem == newItem
        }
    }
}