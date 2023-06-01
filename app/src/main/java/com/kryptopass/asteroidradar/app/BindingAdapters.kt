package com.kryptopass.asteroidradar.app

import android.view.View.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kryptopass.asteroidradar.R
import com.kryptopass.asteroidradar.domain.Asteroid
import com.kryptopass.asteroidradar.domain.ImageOfDay
import com.kryptopass.asteroidradar.ui.AsteroidRecyclerAdapter
import com.kryptopass.asteroidradar.ui.NeoApiStatus

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = imageView.context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("listData")
fun bindAsteroids(recyclerView: RecyclerView, data: List<Asteroid>?) {
    data?.let {
        val adapter = recyclerView.adapter as AsteroidRecyclerAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("imageOfDay")
fun bindImage(imageView: ImageView, imgOfDay: ImageOfDay?) {
    imgOfDay?.let {
        if (imgOfDay.mediaType == "image") {
            val imgUri = imgOfDay.url.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(R.drawable.ic_error)
                .into(imageView)
        }
    }
}

@BindingAdapter("asteroidStatus")
fun asteroidsStatus(progressBar: ProgressBar, apiStatus: NeoApiStatus?) {
    apiStatus.let {
        when (apiStatus) {
            NeoApiStatus.LOADING -> {
                progressBar.visibility = VISIBLE
            }

            NeoApiStatus.DONE -> {
                progressBar.visibility = GONE
            }

            NeoApiStatus.ERROR -> {
                progressBar.visibility = GONE
            }

            else -> {
                progressBar.visibility = GONE
            }
        }
    }
}