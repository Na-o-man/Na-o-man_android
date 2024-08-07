package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.usecase.share_group.InviteCodeUsecase
import com.hgh.na_o_man.domain.usecase.share_group.JoinGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinSideEffect
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinSideEffect._ShowToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class JoinViewModel @Inject constructor(
    val joinGroupUsecase: JoinGroupUsecase,
    val inviteCodeUsecase: InviteCodeUsecase,

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
                // 그룹 가입 이벤트 처리
                val groupId = event.groupId.toInt() // Long을 Int로 변환
                joinGroup(GroupJoinRequestDto(profileId = 1, shareGroupId = groupId)) // GroupJoinRequestDto로 변환
                AddScreenRoute.NAMEINPUT.route
            }
            is JoinContract.JoinEvent.onFind -> {
                // 초대 코드 검증 로직 추가
                validateInviteCode(event.inviteCode)
            }
        }
    }

    // 초대 코드 검증 메서드
    private fun validateInviteCode(inviteCode: String) {
        // 초대 코드가 Long으로 변환 가능한지 확인
        val groupId: Long? = inviteCode.toLongOrNull()
        if (groupId == null) {
            sendEffect({ _ShowToast("유효하지 않은 초대 코드입니다.") })
            return
        }

        viewModelScope.launch {
            inviteCodeUsecase.invoke(groupId).collect { result ->  // groupId를 사용
                when (result) {
                    is RetrofitResult.Success -> {
                        sendEffect({ _ShowToast("초대 코드가 유효합니다.") })
                    }
                    is RetrofitResult.Error -> {
                        sendEffect({ _ShowToast("초대 코드 검증에 실패했습니다: ${result.exception.message}") })
                    }
                    is RetrofitResult.Fail -> {
                        sendEffect({ _ShowToast("초대 코드 검증 실패.") })
                    }
                }
            }
        }
    }

    // URL 유효성 검사 함수
    private fun isValidUrl(url: String): Boolean {
        // 정규 표현식을 사용하여 URL 검증
        val urlPattern = "^(https?://)?(www\\.)?([\\w-]+\\.)+[\\w-]+(/\\S*)?$".toRegex()
        return urlPattern.matches(url)
    }

    // 그룹에 가입하는 메서드
    private fun joinGroup(requestDto: GroupJoinRequestDto) {
        viewModelScope.launch {
            joinGroupUsecase.invoke(requestDto).collect { result ->
                when (result) {
                    is RetrofitResult.Success -> {
                        // 그룹 가입 성공 처리
                        sendEffect ({ _ShowToast("그룹에 성공적으로 가입했습니다.") })
                    }
                    is RetrofitResult.Error -> {
                        // 그룹 가입 오류 처리
                        sendEffect ({ _ShowToast("그룹 가입에 실패했습니다: ${result.exception.message}") })
                    }
                    is RetrofitResult.Fail -> {
                        // 그룹 가입 실패 처리
                        sendEffect ({ _ShowToast("그룹 가입이 실패했습니다.") })
                    }
                    else -> {
                        // 예기치 못한 결과 처리
                        sendEffect ({ _ShowToast("예기치 못한 오류가 발생했습니다.") })
                    }
                }
            }
        }
    }

    // 다이얼로그 상태 업데이트 메서드 추가
    fun setShowDialog(show: Boolean) {
        updateState { copy(showDialog = show) }
    }
}

