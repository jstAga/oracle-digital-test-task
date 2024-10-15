package com.example.movieapp.core.extensions

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.movieapp.R

fun Fragment.fetchColor(id: Int): Int {
  return ContextCompat.getColor(requireContext(), id)
}

fun Fragment.showToast(message: String?) {
  context?.let { Toast.makeText(it, message, Toast.LENGTH_SHORT).show() }
}

fun Fragment.setStatusBarColor(color: Int, animate: Boolean = true) {
  requireActivity().setStatusBarColor(color, animate)
}

fun Fragment.setNavigationBarColor(color: Int) {
  requireActivity().setNavigationBarColor(color)
}

fun Fragment.stringResource(id: Int): String = requireContext().getString(id)

fun NavController.safeNavigate(directions: NavDirections, navOptions: NavOptions? = null) {
  val currentDestination = this.currentDestination
  val actionId = directions.actionId
  val action = currentDestination?.getAction(actionId)
  
  if (action != null && this.currentDestination?.id != actionId) {
    this.navigate(directions, navOptions)
  }
}

fun buildNavOptions(): NavOptions {
  return NavOptions.Builder()
    .setPopUpTo(R.id.homeFragment, false, saveState = true)
    .setLaunchSingleTop(true)
    .setRestoreState(true)
    .build()
}