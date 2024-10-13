package com.example.movieapp.data.paging

import com.example.movieapp.core.base.data.BaseRemotePagingSource
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.remote.ApiService
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
  private val apiService: ApiService
) : BaseRemotePagingSource<MovieModel>({
  apiService.getFilms(page = it)
})
