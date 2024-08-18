package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.annotation.DrawableRes
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
        object NavigateToNextScreen : JoinSideEffect()
        object NavigateToCheckScreen : JoinSideEffect()
        object FinishActivity : JoinSideEffect() // 액티비티 종료를 위한 SideEffect
    }

    // Event
    sealed class JoinEvent : ViewEvent {
        data class ValidateUrl(val inviteCode: String) : JoinEvent()
        object onFind : JoinEvent()
        object onCorrect : JoinEvent()
        object LoadGroupMembers : JoinEvent() // 그룹 멤버 로드 이벤트 추가
        data class onProfileSelected(val profileId: Long) : JoinEvent() // 프로필 선택 이벤트 추가
    }
}
