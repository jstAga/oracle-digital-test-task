package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.data.model.MovieModel

@Dao
interface MovieDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun createMovie(movie: MovieModel)
  
  @Query("SELECT * FROM movies WHERE id = :id")
  suspend fun readMovie(id: String): MovieModel

  @Query("SELECT * FROM movies LIMIT :limit OFFSET :offset")
  suspend fun readMovies(limit: Int, offset: Int): List<MovieModel>
  
  @Update
  suspend fun updateMovie(movie: MovieModel)
  
  @Delete
  suspend fun deleteMovie(movie: MovieModel)
  
  @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :id)")
  suspend fun isSaved(id: Int): Boolean
}