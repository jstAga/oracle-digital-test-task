package com.example.movieapp.core.base.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BaseLocalPagingSource<Value : Any>(
  private val request: suspend (limit: Int, position: Int) -> List<Value>,
  private val limit: Int = 20
) : PagingSource<Int, Value>() {
  
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
    val position = params.key ?: 0
    
    return try {
      val data = request(limit, position * params.loadSize)
      Log.e("aga1", "localLoad: $data")
      LoadResult.Page(
        data = data,
        prevKey = null,
        nextKey = position + 1
      )
    } catch (exception: Exception) {
      Log.e("PagingException", "Exception: ${exception.message}")
      LoadResult.Error(exception)
    }
  }
  
  override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}