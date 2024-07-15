package com.hgh.na_o_man.presentation.ui.main

import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.main.MainContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel<MainViewState, MainSideEffect, MainEvent>(MainViewState) {
    override fun handleEvents(event: MainEvent) {
        when (event) {
            MainEvent.FinishedCreateActivity -> sendEffect({ MainSideEffect.RefreshScreen })
        }
    }
}
