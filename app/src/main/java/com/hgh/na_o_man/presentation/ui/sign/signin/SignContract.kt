package com.hgh.na_o_man.presentation.ui.sign.signin

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListContract

class SignContract {

    data class SignViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val userInfo : Dummy = Dummy(),
    ): ViewState

    sealed class SignSideEffect : ViewSideEffect {
        object NaviAgree : SignContract.SignSideEffect()
        object NaviUser : SignContract.SignSideEffect()
        object NaviUpload : SignContract.SignSideEffect()
        object NaviMain : SignContract.SignSideEffect()
        data class ShowToast(val msg: String) : SignContract.SignSideEffect()
    }

    sealed class SignEvent : ViewEvent {
        object InitSignScreen : SignEvent()
        data class OnClickLogin(val userInfo: Dummy) : SignEvent()
        object OnClickALlAgree : SignEvent()
        object OnClickUpload : SignEvent()
        object OnClickFinish : SignEvent()

    }
}