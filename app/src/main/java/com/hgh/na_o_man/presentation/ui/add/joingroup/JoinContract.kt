package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.annotation.DrawableRes
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

// 멤버 데이터 클래스 정의
data class Member(
    val name: String,
    @DrawableRes val avatarResId: Int // 멤버의 이미지 리소스 ID 추가
)

class JoinContract {

    // ViewState
    data class JoinViewState(
        val isUrlValid: Boolean = true,
        val profileId: Int = 0,
        val shareGroupId: Int = 0,
        val members: List<Member> = emptyList() // 멤버 리스트 추가
    ) : ViewState

    // SideEffect
    sealed class JoinSideEffect : ViewSideEffect {
        data class _ShowToast(val message: String) : JoinSideEffect()
        object NavigateToNextScreen : JoinSideEffect()
    }

    // Event
    sealed class JoinEvent : ViewEvent {
        data class ValidateUrl(val url: String) : JoinEvent()
        object onFind : JoinEvent()
        object onCorrect : JoinEvent()
        object LoadGroupMembers : JoinEvent() // 그룹 멤버 로드 이벤트 추가
        data class onProfileSelected(val profileId: Int) : JoinEvent() // 프로필 선택 이벤트 추가
    }
}
