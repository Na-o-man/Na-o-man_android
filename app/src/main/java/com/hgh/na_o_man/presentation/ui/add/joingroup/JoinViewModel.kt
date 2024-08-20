package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.share_group.JoinGroupUsecase
import com.hgh.na_o_man.domain.usecase.share_group.JoinInviteUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinGroupUsecase: JoinGroupUsecase,
    private val joinInviteUsecase: JoinInviteUsecase,
) : BaseViewModel<JoinContract.JoinViewState, JoinContract.JoinSideEffect, JoinContract.JoinEvent>(
    JoinContract.JoinViewState() // 초기 상태 설정
) {

    init {
        Log.d("ViewModel", "JoinViewModel initialized")
    }

    override fun handleEvents(event: JoinContract.JoinEvent) {
        when (event) {
            is JoinContract.JoinEvent.ValidateUrl -> {
                validateUrl(event.inviteCode)// inviteCode 전달
            }

            is JoinContract.JoinEvent.OnFind -> {
                // 다시 찾기 이벤트 처리
                findAnotherGroup()
            }

            is JoinContract.JoinEvent.OnCorrect -> {
                // 맞아요 이벤트 처리
                sendEffect({ JoinContract.JoinSideEffect.NavigateToNextScreen })
                confirmGroup()
            }

            is JoinContract.JoinEvent.OnProfileSelected -> {
                // 프로필 선택 이벤트 처리
                updateState {
                    copy(profileId = event.profileId)
                }
                sendEffect({ JoinContract.JoinSideEffect.NavigateToNextScreen })
            }

            else -> {}
        }
    }

    private fun validateUrl(inviteCode: String) = viewModelScope.launch {
        try {
            joinInviteUsecase(inviteCode).collect { result ->
                result.onSuccess { response ->
                    updateState {
                        copy(
                            shareGroupId = response.shareGroupId,
                            members = response.profileInfoList.mapIndexed { index, profile ->
                                Member(id = profile.profileId, name = profile.name, avatarUrl = profile.image)
                            },
                            groupName = response.name,
                            inviteCode = inviteCode
                        )
                    }
                    sendEffect({ JoinContract.JoinSideEffect._ShowToast("URL 검증에 성공했습니다.") })
                    sendEffect({JoinContract.JoinSideEffect.NavigateToCheckScreen})
                }.onFail { error ->
                    Log.e("ValidateUrl", "서버와 연결을 실패했습니다. 오류: $error")
                    sendEffect({ JoinContract.JoinSideEffect._ShowToast("URL 검증에 실패했습니다.") })
                }.onException { e ->
                    Log.e("ValidateUrl", "Exception: $e")
                    sendEffect({ JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다. 오류: ${e.message}") })
                }
            }
        } catch (e: Exception) {
            Log.e("ValidateUrl", "Exception: $e")
            sendEffect({ JoinContract.JoinSideEffect._ShowToast("알 수 없는 오류가 발생했습니다.") })
        }
    }



    private fun fetchGroupInfo(profileId: Long, shareGroupId: Int) = viewModelScope.launch {
        try {
            val requestDto = GroupJoinRequestDto(profileId, shareGroupId)

            joinGroupUsecase(requestDto).collect { result ->
                result.onSuccess { response ->
                    updateState {
                        copy(
                            shareGroupId = viewState.value.shareGroupId,
                            profileId = viewState.value.profileId
                        )
                    }

                    sendEffect({
                        JoinContract.JoinSideEffect._ShowToast("그룹 참여에 성공했습니다.\n환영합니다.")
                        JoinContract.JoinSideEffect.FinishActivity
                    })
                }.onFail { error ->
                    Log.e("FetchGroupInfo", "유효하지 않은 그룹 정보: $error")
                    sendEffect({ JoinContract.JoinSideEffect._ShowToast("이미 참여가 완료된 그룹입니다.")
                    })
                }.onException { e ->
                    Log.e("FetchGroupInfo", "Exception: $e")
                    sendEffect({
                        JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다. 오류: ${e.message}")
                    })
                }
            }
        } catch (e: Exception) {
            Log.e("FetchGroupInfo", "Exception: $e")
            sendEffect({
                JoinContract.JoinSideEffect._ShowToast("알 수 없는 오류가 발생했습니다.")
            })
        }
    }

    fun onNextButtonClicked(profileId: Long, shareGroupId: Int) {
        fetchGroupInfo(profileId, shareGroupId)
    }

    private fun confirmGroup() {
    }

    private fun findAnotherGroup() {

    }
}


