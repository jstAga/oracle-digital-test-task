package com.example.movieapp.core.extensions

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

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