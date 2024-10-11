package com.example.movieapp.core.extensions

import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.movieapp.R

fun Activity.setLightStatusBar(isLight: Boolean, isNavLight: Boolean = true) {
  WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
  WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = isNavLight
  window.statusBarColor = if (isLight) fetchColor(R.color.white) else fetchColor(R.color.black)
  window.navigationBarColor = if (isNavLight) fetchColor(R.color.white) else fetchColor(R.color.black)
}

fun Activity.setStatusBarColor(color: Int, animate: Boolean = true) {
  
  if (window.statusBarColor == fetchColor(color)) {
    return
  }
  
  if (animate) {
    // Desired final colors of each bar.
    val statusBarToColor = fetchColor(color)
    val anim = ValueAnimator.ofFloat(0f, 1f)
    anim.addUpdateListener { animation -> // Use animation position to blend colors.
      val position = animation.animatedFraction
      // Apply blended color to the status bar.
      val blended = blendColors(window.statusBarColor, statusBarToColor, position)
      window.statusBarColor = blended
    }
    anim.setDuration(200).start()
  } else {
    window.statusBarColor = fetchColor(color)
  }
}

fun Activity.setNavigationBarColor(color: Int) {
  this.window?.navigationBarColor = fetchColor(color)
}

private fun blendColors(from: Int, to: Int, ratio: Float): Int {
  val inverseRatio = 1f - ratio
  val r = Color.red(to) * ratio + Color.red(from) * inverseRatio
  val g = Color.green(to) * ratio + Color.green(from) * inverseRatio
  val b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio
  return Color.rgb(r.toInt(), g.toInt(), b.toInt())
}

fun Activity.fetchColor(id: Int): Int {
  return ContextCompat.getColor(this, id)
}

fun Activity.showToast(message: String?) {
  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}