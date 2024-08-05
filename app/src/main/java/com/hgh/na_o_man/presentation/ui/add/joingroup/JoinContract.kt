package com.hgh.na_o_man.presentation.ui.add.joingroup

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class JoinContract {

    data class JoinViewState(
        val selectedButtons: List<String> = emptyList() // 선택된 버튼 리스트 추가
        // 필요한 상태 변수를 추가 가능.
    ) : ViewState

    sealed class JoinSideEffect : ViewSideEffect {
    }

    sealed class JoinEvent : ViewEvent {
        data class ChangeNickname(val newNickname: String) : JoinEvent() // 닉네임 변경 이벤트 추가
    }
}
