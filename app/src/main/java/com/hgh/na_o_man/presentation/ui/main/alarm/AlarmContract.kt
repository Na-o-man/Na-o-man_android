package com.hgh.na_o_man.presentation.ui.main.alarm

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AlarmContract {

    data class AlarmViewState(
        val loadState: LoadState = LoadState.SUCCESS,
    ): ViewState

    sealed class AlarmSideEffect : ViewSideEffect {

    }

    sealed class AlarmEvent : ViewEvent {
        object InitAlarmScreen : AlarmEvent()
    }
}