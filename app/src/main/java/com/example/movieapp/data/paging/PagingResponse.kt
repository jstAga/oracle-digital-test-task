package com.example.movieapp.data.paging

import com.google.gson.annotations.SerializedName

open class PagingResponse<T>(
  @SerializedName("page")
  val page: Int?,
  @SerializedName("results")
  val results: MutableList<T>
)