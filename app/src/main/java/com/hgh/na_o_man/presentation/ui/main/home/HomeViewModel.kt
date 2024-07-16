package com.hgh.na_o_man.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState()
) {
    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.InitHomeScreen -> {
                getGroupList()
            }
        }
    }

    init {
        Log.d("리컴포저블","HomeViewModelInit")
    }

    private fun getGroupList() = viewModelScope.launch {

    }
}