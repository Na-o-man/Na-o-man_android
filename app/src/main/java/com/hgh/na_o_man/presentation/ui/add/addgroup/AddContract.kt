package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.MyGroupListResponseDto
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {
    data class AddViewState(
        val selectedButtonCount: Int = 0, // 선택된 버튼 개수
        val textValue: String = "",
        val isLoading: Boolean = false, // 로딩 상태 추가
        val errorMessage: String? = null, // 에러 메시지 추가
        val photos: List<Int> = emptyList(), // 사진 목록 추가
        val shareGroupId: Long? = null, // 공유 그룹 ID 추가
        val myGroups: List<MyGroupListResponseDto> = emptyList() // 내 그룹 목록 추가
    ) : ViewState

    sealed class AddSideEffect : ViewSideEffect {
        object NavigateToNextScreen : AddSideEffect()
        data class ShowToast(val message: String) : AddSideEffect() // ShowToast 사이드 이펙트 추가
        object ShowLoading : AddSideEffect() // 로딩 시작
        object HideLoading : AddSideEffect() // 로딩 종료
        data class ShowError(val error: String) : AddSideEffect() // 에러 메시지 표시
    }

    sealed class AddEvent : ViewEvent {
        data class LoadData(val shareGroupId: Long) : AddEvent() // shareGroupId 포함
        data class OnCloudButtonClicked(val shareGroupId: Long) : AddEvent() // shareGroupId 포함
        data class NextButtonClicked(val selectedButtonCount: Int) : AddEvent() // 다음 버튼 클릭 이벤트
        data class TextInput(val text: String) : AddEvent() // 텍스트 입력 이벤트
        object ClearTextInput : AddEvent() // 텍스트 입력 초기화 이벤트
        data class CreateGroup(val groupAddRequestDto: GroupAddRequestDto) : AddEvent()
        data class DeleteGroup(val groupId: Long) : AddEvent() // 그룹 삭제 이벤트 추가
        object LoadMyGroups : AddEvent() // 내 그룹 목록 로드 이벤트 추가
    }
}
