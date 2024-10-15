package com.example.movieapp.core.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.movieapp.core.base.data.Resource
import com.example.movieapp.core.base.data.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
  
  protected fun <T : Any, S : Any> Flow<PagingData<T>>.gatherPagingRequest(
    mappedData: (data: T) -> S,
  ) = map {
    it.map { data -> mappedData(data) }
  }.cachedIn(viewModelScope)
  
  protected fun <T> Flow<Resource<T>>.collectFlow(
    _state: MutableStateFlow<UIState<T>>,
  ) {
    viewModelScope.launch(Dispatchers.IO) {
      this@collectFlow.collect { res ->
        when (res) {
          is Resource.Error -> {
            if (res.message != null) {
              _state.value = UIState.Error(res.message!!)
            }
          }
          is Resource.Loading -> {
            _state.value = UIState.Loading()
          }
          is Resource.Success -> {
            if (res.data != null) {
              _state.value = UIState.Success(res.data!!)
            }
          }
        }
      }
    }
  }
}