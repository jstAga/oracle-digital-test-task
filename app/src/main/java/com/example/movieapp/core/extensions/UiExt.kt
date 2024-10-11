package com.example.movieapp.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String, placeholderResId: Int) {
  Glide.with(this)
    .load(url)
    .placeholder(placeholderResId)
    .fitCenter()
    .into(this)
}