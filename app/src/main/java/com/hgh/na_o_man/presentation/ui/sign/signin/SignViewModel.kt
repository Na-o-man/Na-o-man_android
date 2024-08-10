package com.hgh.na_o_man.presentation.ui.sign.signin

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.di.util.remote.onError
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.auth.CheckRegistrationUsecase
import com.hgh.na_o_man.domain.usecase.auth.LoginAuthUsecase
import com.hgh.na_o_man.domain.usecase.auth.SignupAuthUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val dataStore: DataStoreUtil,
    private val loginAuthUsecase: LoginAuthUsecase,
    private val signupAuthUsecase: SignupAuthUsecase,
    private val checkRegistrationUsecase: CheckRegistrationUsecase,
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
                signupAuth()
            }

            is SignContract.SignEvent.OnClickLogin -> {
                updateState { copy(authInfo = event.userInfo) }
                checkRegistration()
            }

            SignContract.SignEvent.OnClickUpload -> {
                sendEffect({ SignContract.SignSideEffect.NaviUpload })
            }

            SignContract.SignEvent.OnClickFinish -> {
                sendEffect({ SignContract.SignSideEffect.NaviMain })
            }

            SignContract.SignEvent.OnClickPhotoPicker -> {
                sendEffect({ SignContract.SignSideEffect.NaviPhotoPicker })
            }
        }
    }

    private fun checkRegistration() = viewModelScope.launch {
        try {
            checkRegistrationUsecase(
                LoginRequestDto(
                    authId = viewState.value.authInfo.authId,
                    socialType = viewState.value.authInfo.socialType
                )
            ).collect { result ->
                result.onSuccess {
                    if (it.isRegistered) {
                        loginAuth()
                    } else {
                        sendEffect({ SignContract.SignSideEffect.NaviAgree })
                    }
                }.onFail {
                    sendEffect({ SignContract.SignSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun loginAuth() = viewModelScope.launch {
        try {
            loginAuthUsecase(
                LoginRequestDto(
                    authId = viewState.value.authInfo.authId,
                    socialType = viewState.value.authInfo.socialType
                )
            ).collect { result ->
                result.onSuccess {
                    dataStore.setAccessToken(it.accessToken)
                    dataStore.setRefreshToken(it.refreshToken)
                    dataStore.setAutoLogin(true)
                    sendEffect({ SignContract.SignSideEffect.NaviMain })
                }.onFail {
                    sendEffect({ SignContract.SignSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun signupAuth() = viewModelScope.launch {
        try {
            signupAuthUsecase(
                SignUpRequestDto(
                    authId = viewState.value.authInfo.authId,
                    email = viewState.value.authInfo.email,
                    name = viewState.value.authInfo.name,
                    image = viewState.value.authInfo.profileUrl,
                    socialType = viewState.value.authInfo.socialType,
                    marketingAgreed = true
                )
            ).collect { result ->
                result.onSuccess {
                    dataStore.setAccessToken(it.accessToken)
                    dataStore.setRefreshToken(it.refreshToken)
                    dataStore.setAutoLogin(true)
                    sendEffect({ SignContract.SignSideEffect.NaviUser })
                }.onFail {
                    sendEffect({ SignContract.SignSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    fun patchUris(uris: List<Uri>) = viewModelScope.launch {
        updateState { copy(photos = uris) }
    }
}