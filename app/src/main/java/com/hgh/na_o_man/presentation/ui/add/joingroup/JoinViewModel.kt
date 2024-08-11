package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.R
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.share_group.JoinGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.Mustard
import com.hgh.na_o_man.presentation.theme.SlateGray
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

            is JoinContract.JoinEvent.onFind -> {
                // 다시 찾기 이벤트 처리
                sendEffect({ JoinContract.JoinSideEffect._ShowToast("다시 찾는 중입니다.") })
            }

            is JoinContract.JoinEvent.onCorrect -> {
                // 맞아요 이벤트 처리
                sendEffect({ JoinContract.JoinSideEffect.NavigateToNextScreen })
            }
            is JoinContract.JoinEvent.LoadGroupMembers -> {
                loadGroupMembers()
            }
            is JoinContract.JoinEvent.onProfileSelected -> {
                // 프로필 선택 이벤트 처리
                updateState { copy(profileId = event.profileId) }
                sendEffect ({ JoinContract.JoinSideEffect.NavigateToNextScreen })
            }
        }
    }

    private fun validateUrl(url: String) = viewModelScope.launch {
        // URL이 http 또는 https로 시작하는지 확인
        if (url.matches(Regex("^(http|https)://.*"))) {
            try {
                // 유효한 URL로 처리
                val requestDto = GroupJoinRequestDto(
                    profileId = 0,
                    shareGroupId = 0
                )

                joinGroupUsecase(requestDto).collect { result ->
                    result
                        .onSuccess {
                            updateState { copy(isUrlValid = true) }
                            sendEffect({ JoinContract.JoinSideEffect._ShowToast("URL 검증에 성공했습니다.") })
                            loadGroupMembers() // URL 검증 성공 시 그룹 멤버 로드
                        }
                        .onFail {
                            updateState { copy(isUrlValid = false) }
                            sendEffect({ JoinContract.JoinSideEffect._ShowToast("URL 검증에 실패했습니다.") })
                        }
                        .onException { exception ->
                            Log.e("예외받기", "$exception")
                            sendEffect({ JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다.") })
                        }
                }
            } catch (e: Exception) {
                Log.e("예외받기", "$e")
                sendEffect({ JoinContract.JoinSideEffect._ShowToast("서버와 연결을 실패했습니다.") })
            }
        } else {
            // URL이 유효하지 않음
            sendEffect({ JoinContract.JoinSideEffect._ShowToast("유효한 URL을 입력하세요.") })
        }
    }

    private fun loadGroupMembers() = viewModelScope.launch {
        // 여기서 그룹 멤버 정보를 로드하는 로직을 수행합니다.
        val members = getGroupMembers() // 실제 데이터 로드 로직 필요
        updateState { copy(members = members) }
    }

    // 샘플 그룹 멤버 로드 함수
    private fun getGroupMembers(): List<Member> {
        return listOf(
            Member("홍길동", R.drawable.ic_add_group_avatar_94),
            Member("홍길은", R.drawable.ic_add_group_avatar_94),
            Member("홍길금", R.drawable.ic_add_group_avatar_94)
        )
    }
}

