package com.hgh.na_o_man.presentation.ui.sign.signin

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
) : BaseViewModel<SignContract.SignViewState, SignContract.SignSideEffect, SignContract.SignEvent>(
    SignContract.SignViewState()
) {

    init {
        Log.d("리컴포저블", "SignViewModel")
    }

    override fun handleEvents(event: SignContract.SignEvent) {
        when (event) {
            is SignContract.SignEvent.InitSignScreen -> {

            }

            SignContract.SignEvent.OnClickALlAgree -> {
                sendEffect({ SignContract.SignSideEffect.NaviUser })
            }

            is SignContract.SignEvent.OnClickLogin -> {
                updateState { copy(userInfo = event.userInfo) }
                sendEffect({ SignContract.SignSideEffect.NaviAgree })
            }

            SignContract.SignEvent.OnClickUpload -> {
                sendEffect({ SignContract.SignSideEffect.NaviUpload })
            }

            SignContract.SignEvent.OnClickFinish -> {

            }
        }
    }


}