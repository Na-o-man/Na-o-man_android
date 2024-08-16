package com.hgh.na_o_man.presentation.ui.main.create

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.member.SamplePhotoUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMainViewModel @Inject constructor(
    private val checkSampleUsecase: SamplePhotoUsecase
) : BaseViewModel<AddMainContract.AddMainViewState, AddMainContract.AddMainSideEffect, AddMainContract.AddMainEvent>(
    AddMainContract.AddMainViewState()
) {

    init {
        checkSamplePhoto()
        Log.d("리컴포저블", "AddMainViewModel")
    }

    override fun handleEvents(event: AddMainContract.AddMainEvent) {
        when (event) {
            AddMainContract.AddMainEvent.OnClickAdd -> {
                if (viewState.value.haveSample) {
                    sendEffect({ AddMainContract.AddMainSideEffect.NaviAdd })
                } else {
                    sendEffect({ AddMainContract.AddMainSideEffect.ShowToast("샘플 사진은 필수 입니다.") })
                    sendEffect({ AddMainContract.AddMainSideEffect.NaviSampleUpload })
                }
            }

            AddMainContract.AddMainEvent.OnClickBack -> {
                sendEffect({ AddMainContract.AddMainSideEffect.NaviBack })
            }

            AddMainContract.AddMainEvent.OnClickJoin -> {
                if (viewState.value.haveSample) {
                    sendEffect({ AddMainContract.AddMainSideEffect.NaviJoin })
                } else {
                    sendEffect({ AddMainContract.AddMainSideEffect.ShowToast("샘플 사진은 필수 입니다.") })
                    sendEffect({ AddMainContract.AddMainSideEffect.NaviSampleUpload })
                }
            }
        }
    }

    private fun checkSamplePhoto() {
        viewModelScope.launch {
            try {
                checkSampleUsecase().collect { result ->
                    result.onSuccess {
                        updateState { copy(haveSample = it.hasSamplePhoto) }
                    }.onFail {
                        sendEffect({ AddMainContract.AddMainSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                    }.onException {
                        throw it
                    }
                }
            } catch (e: Exception) {
                Log.e("예외받기", "$e")
            }
        }

    }
}
