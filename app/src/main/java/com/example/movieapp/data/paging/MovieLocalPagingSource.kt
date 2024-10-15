package com.example.movieapp.data.paging

import com.example.movieapp.core.base.data.BaseLocalPagingSource
import com.example.movieapp.core.base.data.BaseRemotePagingSource
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.remote.ApiService
import javax.inject.Inject

class MovieLocalPagingSource @Inject constructor(
  private val dao: MovieDao
) : BaseLocalPagingSource<MovieModel>({ limit, position ->
  dao.readMovies(limit, position)
})
