package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {

    data class AddViewState(
        val loadState: LoadState = LoadState.SUCCESS,
    ) : ViewState

    sealed class AddSideEffect : ViewSideEffect {
        object NavigateToMembersInviteScreen : AddSideEffect() // 여기서 변경
    }

    sealed class AddEvent : ViewEvent {
        object InitMembersInviteScreen : AddEvent()
        object OnBackButtonClicked : AddEvent() // 뒤로가기 이벤트 추가
    }
}