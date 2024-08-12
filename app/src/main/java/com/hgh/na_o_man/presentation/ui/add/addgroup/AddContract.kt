package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {
    data class AddViewState(
        val groupName: String = "",
        val memberNames: List<String> = emptyList(),
        val memberCount: Int = 0,
        val selectedAttributes: List<String> = emptyList(), // 새로운 속성 필드 추가
        val place: String = "", // 공간 업뎃,
        val isGroupCreated: Boolean = false, // 그룹 생성 완료 상태 추가
        val inviteLink: String = "", // 초대 링크 추가
        val isLoading: Boolean = false // 로딩 상태 추가
    ) : ViewState

    sealed class AddEvent : ViewEvent {
        data class AddMember(val name: String) : AddEvent()
        object CreateGroup : AddEvent()
        data class RemoveMember(val name: String) : AddEvent()
        data class UpdateSelectedAttributes(val attributes: List<String>) : AddEvent()
    }

    sealed class AddSideEffect : ViewSideEffect {
        object NavigateToNextScreen : AddSideEffect()
        data class ShowToast(val message: String) : AddSideEffect()
        data class ShowError(val error: String) : AddSideEffect()
    }
}
