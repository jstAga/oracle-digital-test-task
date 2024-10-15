package com.example.movieapp.ui.home

import com.example.movieapp.core.base.ui.BaseViewModel
import com.example.movieapp.repository.HomeRepository
import com.example.movieapp.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: HomeRepository,
  private val roomRepository: RoomRepository
) : BaseViewModel() {
  
  fun getFilms() = homeRepository.getFilms().gatherPagingRequest { it }
  
  fun readFilms() = roomRepository.readMovies().gatherPagingRequest { it }
}