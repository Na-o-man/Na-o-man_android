package com.hgh.na_o_man.presentation.ui.sign.signin

import android.net.Uri
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class SignContract {

    data class SignViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val authInfo: AuthInfoModel = AuthInfoModel(),
        val photos: List<Uri> = listOf()
    ) : ViewState

    sealed class SignSideEffect : ViewSideEffect {
        object NaviAgree : SignContract.SignSideEffect()
        object NaviUser : SignContract.SignSideEffect()
        object NaviUpload : SignContract.SignSideEffect()
        object NaviPhotoPicker : SignContract.SignSideEffect()
        object NaviMain : SignContract.SignSideEffect()
        data class ShowToast(val msg: String) : SignContract.SignSideEffect()
    }

    sealed class SignEvent : ViewEvent {
        object InitSignScreen : SignEvent()
        data class OnClickLogin(val userInfo: AuthInfoModel) : SignEvent()
        object OnClickALlAgree : SignEvent()
        object OnClickUpload : SignEvent()
        object OnClickPhotoPicker : SignEvent()
        object OnClickFinish : SignEvent()

    }
}