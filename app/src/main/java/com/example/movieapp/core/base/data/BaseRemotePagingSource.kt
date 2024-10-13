package com.example.movieapp.core.base.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.paging.PagingResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRemotePagingSource<Value : Any>(
  private val request: suspend (position: Int) -> Response<PagingResponse<Value>>,
) : PagingSource<Int, Value>() {
  
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
    val position = params.key ?: 0
    
    return try {
      val response = request(position)
      val data = response.body()?.results ?: emptyList()
      LoadResult.Page(
        data = data,
        prevKey = null,
        nextKey = position + 1
      )
    } catch (exception: IOException) {
      Log.e("PagingException", "IOException: ${exception.message}")
      LoadResult.Error(exception)
    } catch (exception: HttpException) {
      Log.e("PagingException", "HttpException: ${exception.message}" )
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