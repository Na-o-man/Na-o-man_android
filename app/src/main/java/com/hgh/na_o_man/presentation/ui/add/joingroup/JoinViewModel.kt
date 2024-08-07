package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.share_group.JoinGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinGroupUsecase: JoinGroupUsecase
) : BaseViewModel<JoinContract.JoinViewState, JoinContract.JoinSideEffect, JoinContract.JoinEvent>(
    JoinContract.JoinViewState() // 초기 상태 설정
) {

    init {
        Log.d("ViewModel", "JoinViewModel initialized")
    }

    override fun handleEvents(event: JoinContract.JoinEvent) {
        when (event) {
            is JoinContract.JoinEvent.ValidateUrl -> {
                validateUrl(event.url)
            }
            // 다른 이벤트 처리
        }
    }



    private fun validateUrl(url: String) = viewModelScope.launch {
        try {
            // GroupJoinRequestDto 객체 생성 (필요한 데이터가 무엇인지 확인하고 설정합니다)
            val requestDto = GroupJoinRequestDto(
                profileId = 0,  // 적절한 값을 설정합니다.
                shareGroupId = 0 // 적절한 값을 설정합니다.
            )

            // 유스케이스 호출 및 결과 처리
            joinGroupUsecase(requestDto).collect { result ->
                result
                    .onSuccess {
                        // 성공 처리
                        updateState { copy(isUrlValid = true) }
                    }
                    .onFail {
                        // 실패 처리
                        updateState { copy(isUrlValid = false) }
                        sendEffect ({ JoinContract.JoinSideEffect._ShowToast("URL 검증에 실패했습니다.") })
                    }
                    .onException { exception ->
                        // 예외 처리
                        Log.e("예외받기", "$exception")
                        sendEffect ({ JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다.") })
                    }
            }
        } catch (e: Exception) {
            // 메서드 외부의 예외 처리
            Log.e("예외받기", "$e")
            sendEffect ({ JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다.") })
        }
    }
}
