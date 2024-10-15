package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.MovieModel

@Database(
  entities = [MovieModel::class],
  version = 1,
  exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
  
  abstract fun movieDao(): MovieDao
}