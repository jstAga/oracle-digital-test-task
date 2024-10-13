package com.example.movieapp.ui.detail

import com.example.movieapp.core.base.ui.BaseViewModel
import com.example.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(repository: HomeRepository) : BaseViewModel() {
}