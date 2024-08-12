package com.hgh.na_o_man.presentation.ui.sign.signin

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoNameListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoSampleUrlListDto
import com.hgh.na_o_man.data.dto.photo.request.PhotoUrlListDto
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.di.util.remote.onError
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.di.util.s3.S3Util
import com.hgh.na_o_man.domain.usecase.auth.CheckRegistrationUsecase
import com.hgh.na_o_man.domain.usecase.auth.LoginAuthUsecase
import com.hgh.na_o_man.domain.usecase.auth.SignupAuthUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoPreSignedUrlUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoSampleUploadUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val dataStore: DataStoreUtil,
    private val loginAuthUsecase: LoginAuthUsecase,
    private val signupAuthUsecase: SignupAuthUsecase,
    private val checkRegistrationUsecase: CheckRegistrationUsecase,
    private val getS3PreSignedUrlUsecase: PhotoPreSignedUrlUsecase,
    private val uploadSamplePhotoUsecase: PhotoSampleUploadUsecase,
    private val s3Util: S3Util,
) : BaseViewModel<SignContract.SignViewState, SignContract.SignSideEffect, SignContract.SignEvent>(
    SignContract.SignViewState()
) {

    init {
        Log.d("리컴포저블", "SignViewModel")
    }

    override fun handleEvents(event: SignContract.SignEvent) {
        when (event) {
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
                uploadSamplePhoto()
            }

            SignContract.SignEvent.OnClickPhotoPicker -> {
                sendEffect({ SignContract.SignSideEffect.NaviPhotoPicker })
            }

            is SignContract.SignEvent.OnPhotoSelected -> {
                updateState { copy(photos = event.photos) }
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

    private fun uploadSamplePhoto() = viewModelScope.launch {
        if (viewState.value.photos.size == 2) {
            try {
                val successUrl = mutableListOf<String>()

                val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                val currentTime = dateFormat.format(Date())


                val files = viewState.value.photos.mapIndexed { index, uri ->
                    s3Util.convertUriToJpegFile(uri, "${currentTime}_${index}")
                }

                getS3PreSignedUrlUsecase(
                    PhotoNameListDto(
                        shareGroupId = 1,
                        photoNameList = files.map { it.name })
                ).collect { result ->
                    result.onSuccess {
                        files.zip(it.preSignedUrlInfoList).forEach {
                            s3Util.uploadImageWithPreSignedUrl(it.first, it.second.preSignedUrl)
                                .onSuccess { _ ->
                                    successUrl.add(it.second.photoUrl)
                                }
                        }
                        if (successUrl.size == 2) {
                            uploadSamplePhotoUsecase(
                                PhotoSampleUrlListDto(
                                    photoUrlList = successUrl
                                )
                            ).collect { result ->
                                result.onSuccess {
                                    sendEffect({ SignContract.SignSideEffect.NaviMain })
                                }.onFail {
                                    sendEffect({ SignContract.SignSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                                }.onException {
                                    throw it
                                }
                            }
                        } else {
                            sendEffect({ SignContract.SignSideEffect.ShowToast("사진 2개 s3 업로드 실패") })
                        }
                    }.onException {
                        throw it
                    }
                }
            } catch (e: Exception) {
                Log.e("예외받기", "$e")
            }

        } else {
            sendEffect({ SignContract.SignSideEffect.ShowToast("사진이 2개 필요합니다.") })
        }
    }
}