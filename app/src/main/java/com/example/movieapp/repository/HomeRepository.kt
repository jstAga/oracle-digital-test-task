package com.example.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.core.base.ui.BaseRepository
import com.example.movieapp.data.paging.MovieRemotePagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
  private val moviePagingSource: MovieRemotePagingSource
) : BaseRepository() {
  
  fun getMovies() = Pager(
    config = PagingConfig(
      pageSize = 30,
      prefetchDistance = 10
    ),
    pagingSourceFactory = {
      moviePagingSource
    }
  ).flow
}