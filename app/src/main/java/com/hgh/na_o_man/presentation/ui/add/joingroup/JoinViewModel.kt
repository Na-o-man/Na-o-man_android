package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute
import com.hgh.na_o_man.presentation.ui.add.JoinScreenRoute
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinSideEffect
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinSideEffect._ShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class JoinViewModel @Inject constructor(
) : BaseViewModel<JoinContract.JoinViewState, JoinSideEffect, JoinContract.JoinEvent>(
    JoinContract.JoinViewState() // 초기 상태 설정
) {

    init {
        Log.d("리컴포저블", "JoinViewModel")
    }

    override fun handleEvents(event: JoinContract.JoinEvent) {
        when (event) {
            is JoinContract.JoinEvent.UpdateUrl -> {
                // URL 업데이트
                updateState { copy(url = event.newUrl) }
            }
            JoinContract.JoinEvent.ValidateUrl -> {
                // URL 검증 로직
                if (isValidUrl(viewState.value.url)) {
                    updateState { copy(isUrlValid = true) }
                } else {
                    sendEffect ({ _ShowToast("유효한 URL을 입력하세요.") })
                }
            }
            JoinContract.JoinEvent.ShowConfirmationDialog -> {
                updateState { copy(showDialog = true) }
            }
            is JoinContract.JoinEvent.onCorrect -> {
                AddScreenRoute.NAMEINPUT.route
            }
            is JoinContract.JoinEvent.onFind -> {
            }
        }
    }

    // URL 유효성 검사 함수
    private fun isValidUrl(url: String): Boolean {
        // 정규 표현식을 사용하여 URL 검증
        val urlPattern = "^(https?://)?(www\\.)?([\\w-]+\\.)+[\\w-]+(/\\S*)?$".toRegex()
        return urlPattern.matches(url)
    }

    // 다이얼로그 상태 업데이트 메서드 추가
    fun setShowDialog(show: Boolean) {
        updateState { copy(showDialog = show) }
    }
}