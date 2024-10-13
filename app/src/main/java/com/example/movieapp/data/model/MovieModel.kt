package com.example.movieapp.data.model

import com.example.movieapp.core.base.data.IBaseDiffModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieModel(
  val adult: Boolean,
  @SerializedName("backdrop_path")
  val backdropPath: String,
  @SerializedName("genre_ids")
  val genreIds: List<Int>,
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
