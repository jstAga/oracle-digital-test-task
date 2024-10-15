package com.example.movieapp.data.remote

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.paging.PagingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {
  
  @GET("discover/movie")
  suspend fun getMovies(
    @Query("page") page: Int,
    @Header("Authorization") token: String = "Bearer ${BuildConfig.API_KEY}"
  ): Response<PagingResponse<MovieModel>>
}