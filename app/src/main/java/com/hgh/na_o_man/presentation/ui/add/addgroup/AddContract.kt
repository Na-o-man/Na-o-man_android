package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {

    data class AddViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val nickname: String = "", // 닉네임 추가
        val isCurrentUserInGroup: Boolean = false, // 현재 사용자가 그룹에 속해있는지 여부
        val currentUserNickname: String = "", // 현재 사용자의 닉네임
        val memberCount: Int = 0, // 인원 수 추가
        val selectedButtons: List<String> = emptyList() // 선택된 버튼 리스트 추가
        // 필요한 상태 변수를 추가 가능.
    ) : ViewState

    sealed class AddSideEffect : ViewSideEffect {
        object NavigateToMembersInviteScreen : AddSideEffect() // 멤버 초대 화면으로 이동
        object NavigateToAddGroup2Screen : AddSideEffect() // AddGroup2 화면으로 이동
        object NavigateToNextScreen : AddSideEffect() // 다음 화면으로 이동
        data class ShowErrorMessage(val message: String) : AddSideEffect() // 에러 메시지 표시
        object ShowNicknameChangedToast : AddSideEffect() // 닉네임 변경 알림
    }

    sealed class AddEvent : ViewEvent {
        object InitAddScreen : AddEvent() // 이름 변경
        data class ChangeNickname(val newNickname: String) : AddEvent() // 닉네임 변경 이벤트 추가
        object AddGroup2 : AddEvent() // AddGroup2 기능 추가
        data class AddGroup1(val textValue: String) : AddEvent() // AddGroup1 기능 추가
        data class AddGroup3(val selectedButtons: List<String>, val inputText: String) : AddEvent() // AddGroup3 이벤트 추가
        data class AddGroup4(val textValue: String) : AddEvent() // AddGroup4로 변경
        object AddGroup5 : AddEvent() // AddGroup5
        object AddGroup6 : AddEvent() // 다음 스크린으로 이동하는 이벤트 - AddGroup6
        object AddGroup7 : AddEvent() // 새로운 이벤트 추가 - AddGroup7
        object AddGroup8 : AddEvent() // "다시 찾기" 이벤트 추가
    }
}
