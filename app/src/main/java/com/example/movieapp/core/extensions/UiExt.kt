package com.example.movieapp.core.extensions

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.movieapp.R

fun ImageView.load(
  url: String,
  placeholder: Int = R.color.gray,
  optimizePicture : Boolean = true
) {
  if (optimizePicture){
    Glide.with(this)
      .load(url)
      .fitCenter()
      .override(width, height)
      .placeholder(placeholder)
      .downsample(DownsampleStrategy.CENTER_INSIDE)
      .diskCacheStrategy(DiskCacheStrategy.DATA)
      .into(this)
  } else {
    Glide.with(this)
      .load(url)
      .override(width, height)
      .placeholder(placeholder)
      .into(this)
  }
}

fun ImageView.setDrawable(context : Context, drawable: Int) {
  this.setImageDrawable(ContextCompat.getDrawable(context, drawable))
}