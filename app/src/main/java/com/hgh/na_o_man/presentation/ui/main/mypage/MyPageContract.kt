package com.hgh.na_o_man.presentation.ui.main.mypage

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class MyPageContract {

    data class MyPageViewState(
        val loadState: LoadState = LoadState.SUCCESS,
    ): ViewState

    sealed class MyPageSideEffect : ViewSideEffect {

    }

    sealed class MyPageEvent : ViewEvent {
        object InitMyPageScreen : MyPageEvent()
    }
}