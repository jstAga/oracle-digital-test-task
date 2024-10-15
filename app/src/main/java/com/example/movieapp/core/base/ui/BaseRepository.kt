package com.example.movieapp.core.base.ui

import com.example.movieapp.core.base.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {
  
  protected fun <T> doRequest(response: suspend () -> T): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    try {
      emit(Resource.Success(response()))
    } catch (ioException: IOException) {
      emit(Resource.Error(ioException.localizedMessage ?: "unknown exception"))
    }
  }.flowOn(Dispatchers.IO)
}