package tk.atna.weatherapp.stuff

import android.content.Context
import android.content.Intent
import tk.atna.weatherapp.presentation.details.DetailsActivity

fun Context.openDetailsScreen(day: Int) {
    startActivity(
        Intent(this, DetailsActivity::class.java)
            .putExtra(DetailsActivity.DETAILS_DAY, day)
    )
}