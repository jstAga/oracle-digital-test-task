package com.example.movieapp.core.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseViewModel : ViewModel() {
  
  protected fun <T : Any, S : Any> Flow<PagingData<T>>.gatherPagingRequest(
    mappedData: (data: T) -> S,
  ) = map {
    it.map { data -> mappedData(data) }
  }.cachedIn(viewModelScope)
}