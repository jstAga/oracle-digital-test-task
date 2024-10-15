package com.example.movieapp.ui.detail

import com.example.movieapp.core.base.data.UIState
import com.example.movieapp.core.base.ui.BaseViewModel
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val roomRepository: RoomRepository
) : BaseViewModel() {
  
  private val _createMovieState = MutableStateFlow<UIState<Unit>>(UIState.Idle())
  val createMovieState = _createMovieState.asStateFlow()
  
  private val _deleteMovieState = MutableStateFlow<UIState<Unit>>(UIState.Idle())
  val deleteMovieState = _deleteMovieState.asStateFlow()
  
  private val _isMovieSavedState = MutableStateFlow<UIState<Boolean>>(UIState.Idle())
  val isMovieSavedState = _isMovieSavedState.asStateFlow()
  
  fun createMovie(movieModel: MovieModel) = roomRepository.createMovie(movieModel).collectFlow(_createMovieState)
  
  fun deleteMovie(movieModel: MovieModel) = roomRepository.deleteMovie(movieModel).collectFlow(_createMovieState)
  
  fun isMovieSaved(id: Int) = roomRepository.isMovieSaved(id).collectFlow(_isMovieSavedState)
}