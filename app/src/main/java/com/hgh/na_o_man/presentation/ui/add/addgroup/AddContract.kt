package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {
    data class AddViewState(
        val groupName: String = "",
        val memberNames: List<String> = emptyList(),
        val memberCount: Int = 0,
        val selectedAttributes: List<String> = emptyList(), // 새로운 성격 필드
        val place: String = "", // 공간 업뎃,
        val isGroupCreated: Boolean = false, // 그룹 생성 완료 상태
        val inviteLink: String = "", // 초대 링크
        val isLoading: Boolean = false // 로딩 상태
    ) : ViewState

    sealed class AddEvent : ViewEvent {
        data class AddMember(val name: String) : AddEvent()
        data object CreateGroup : AddEvent()
        data class RemoveMember(val name: String) : AddEvent()
        data class UpdateSelectedAttributes(val attributes: List<String>) : AddEvent()
        data object Back : AddEvent()
    }

    sealed class AddSideEffect : ViewSideEffect {
        data object NavigateToNextScreen : AddSideEffect()
        data class ShowToast(val message: String) : AddSideEffect()
        data class ShowError(val error: String) : AddSideEffect()
        data object Back : AddSideEffect()
        data object FinishActivity : AddSideEffect()
    }
}

