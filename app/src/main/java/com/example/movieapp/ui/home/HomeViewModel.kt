package com.example.movieapp.ui.home

import com.example.movieapp.core.base.BaseViewModel
import com.example.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : BaseViewModel() {
}