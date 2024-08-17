package com.hgh.na_o_man.presentation.ui.main.mypage

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.domain.model.member.SearchSuccessModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState
import com.hgh.na_o_man.presentation.ui.sign.signin.SignContract

class MyPageContract {

    data class MyPageViewState(
        val loadState: LoadState = LoadState.LOADING,
        val userInfo : AuthInfoModel = AuthInfoModel(),
        val isDialogVisible: Boolean = false,
        val dialogMod : DialogMode = DialogMode.LOGOUT,
    ): ViewState

    sealed class MyPageSideEffect : ViewSideEffect {
        object NaviBack : MyPageSideEffect()
        object LogOut : MyPageSideEffect()
        object SignOut : MyPageSideEffect()
        data class ShowToast(val msg: String) : MyPageSideEffect()

    }

    sealed class MyPageEvent : ViewEvent {
        object OnClickBack : MyPageEvent()
        object OnClickLogOut : MyPageEvent()
        object OnClickSignOut : MyPageEvent()
        object OnDialogClosed : MyPageEvent()
        object OnClickDialogLogOut : MyPageEvent()
        object OnClickDialogSignOut : MyPageEvent()
    }
}

enum class DialogMode(val title : String) {
    SING_OUT("탈퇴하시겠습니끼?\n탈퇴 시 데이터는 복구할 수 없습니다."),
    LOGOUT("로그아웃 하시겠습니까?"),
}