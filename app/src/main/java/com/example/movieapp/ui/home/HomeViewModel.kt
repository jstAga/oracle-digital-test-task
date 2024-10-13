package com.example.movieapp.ui.home

import com.example.movieapp.core.base.ui.BaseViewModel
import com.example.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: HomeRepository
) : BaseViewModel() {
  
  fun getFilms() = repository.getFilms().gatherPagingRequest { it }
}