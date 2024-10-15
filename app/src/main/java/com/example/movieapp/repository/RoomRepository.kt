package com.example.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.core.base.ui.BaseRepository
import com.example.movieapp.data.local.MovieDao
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.paging.MovieLocalPagingSource
import javax.inject.Inject

class RoomRepository @Inject constructor(
  private val dao: MovieDao,
  private val moviePagingSource: MovieLocalPagingSource
) : BaseRepository() {
  
  fun createMovie(movieModel: MovieModel) = doRequest { dao.createMovie(movieModel) }
  
  fun readMovies() = Pager(
    config = PagingConfig(
      pageSize = 20,
      prefetchDistance = 7
    ),
    pagingSourceFactory = {
      moviePagingSource
    }
  ).flow
  
  fun deleteMovie(movieModel: MovieModel) = doRequest { dao.deleteMovie(movieModel) }
  
  fun isMovieSaved(id : Int) = doRequest { dao.isSaved(id) }
}