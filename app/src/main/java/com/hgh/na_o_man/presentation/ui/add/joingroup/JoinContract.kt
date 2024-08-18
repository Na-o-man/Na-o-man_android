package com.hgh.na_o_man.presentation.ui.add.joingroup

import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

data class Member(
    val id: Long, // ID 추가
    val name: String,
    val avatarUrl: String // URL로 변경
)

class JoinContract {

    // ViewState
    data class JoinViewState(
        val groupName: String = "",
        val isUrlValid: Boolean = false,
        val profileId: Long = 0,
        val shareGroupId: Int = 0,
        val members: List<Member> = emptyList(), // 멤버 리스트 추가
        val inviteCode: String = "", // inviteCode 추가
    ) : ViewState

    // SideEffect
    sealed class JoinSideEffect : ViewSideEffect {
        data class _ShowToast(val message: String) : JoinSideEffect()
        data object NavigateToNextScreen : JoinSideEffect()
        data object NavigateToCheckScreen : JoinSideEffect()
        data object FinishActivity : JoinSideEffect() // 액티비티 종료
    }

    // Event
    sealed class JoinEvent : ViewEvent {
        data class ValidateUrl(val inviteCode: String) : JoinEvent()
        data object OnFind : JoinEvent()
        data object OnCorrect : JoinEvent()
        data object LoadGroupMembers : JoinEvent() // 그룹 멤버 로드
        data class OnProfileSelected(val profileId: Long) : JoinEvent() // 프로필 선택
    }
}
