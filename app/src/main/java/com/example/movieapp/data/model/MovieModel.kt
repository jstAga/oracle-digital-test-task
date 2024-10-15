package com.example.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.core.base.data.IBaseDiffModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
data class MovieModel(
  val adult: Boolean,
  @SerializedName("backdrop_path")
  val backdropPath: String,
  @PrimaryKey(autoGenerate = false)
  override val id: Int,
  @SerializedName("original_language")
  val originalLanguage: String,
  val overview: String,
  val popularity: Float,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("release_date")
  val releaseData: String,
  val title: String,
  val video: Boolean,
  @SerializedName("vote_average")
  val voteAverage: Float,
  @SerializedName("vote_count")
  val voteCount: Int,
) : IBaseDiffModel<Int>, Serializable
