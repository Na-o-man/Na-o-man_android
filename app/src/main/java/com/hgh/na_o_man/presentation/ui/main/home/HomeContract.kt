package com.hgh.na_o_man.presentation.ui.main.home

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class HomeContract {

    data class HomeViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupList: List<Dummy> = listOf(),
    ): ViewState

    sealed class HomeSideEffect : ViewSideEffect {

    }

    sealed class HomeEvent : ViewEvent {
        object InitHomeScreen : HomeEvent()
    }
}