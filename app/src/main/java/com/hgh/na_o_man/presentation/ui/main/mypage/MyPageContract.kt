package com.hgh.na_o_man.presentation.ui.main.mypage

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState
import com.hgh.na_o_man.presentation.ui.sign.signin.SignContract

class MyPageContract {

    data class MyPageViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val userInfo : Dummy = Dummy()
    ): ViewState

    sealed class MyPageSideEffect : ViewSideEffect {
        object NaviBack : MyPageSideEffect()
        object LogOut : MyPageSideEffect()
        object SignOut : MyPageSideEffect()
        data class ShowToast(val msg: String) : MyPageSideEffect()

    }

    sealed class MyPageEvent : ViewEvent {
        object InitMyPageScreen : MyPageEvent()
        object OnClickBack : MyPageEvent()
        object OnClickLogOut : MyPageEvent()
        object OnClickSignOut : MyPageEvent()
    }
}