package com.hgh.na_o_man.presentation.ui.add.joingroup

import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class JoinContract {

    // ViewState
    data class JoinViewState(
        val isUrlValid: Boolean = false,
        val profileId: Int = 0,
        val shareGroupId: Int = 0
    ) : ViewState

    // SideEffect
    sealed class JoinSideEffect : ViewSideEffect {
        data class _ShowToast(val message: String) : JoinSideEffect()
    }

    // Event
    sealed class JoinEvent : ViewEvent {
        data class ValidateUrl(val url: String) : JoinEvent()
    }
}
