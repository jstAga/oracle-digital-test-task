package com.example.movieapp.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.movieapp.R

fun ImageView.load(
  url: String,
  width: Int,
  height: Int,
  placeholder: Int = R.color.gray
) {
  Glide.with(this)
    .load(url)
    .fitCenter()
    .override(width, height)
    .placeholder(placeholder)
    .downsample(DownsampleStrategy.CENTER_INSIDE)
    .diskCacheStrategy(DiskCacheStrategy.DATA)
    .into(this)
}