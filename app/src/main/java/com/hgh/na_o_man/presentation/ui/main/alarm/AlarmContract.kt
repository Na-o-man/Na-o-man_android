package com.hgh.na_o_man.presentation.ui.main.alarm

import com.hgh.na_o_man.domain.model.AlarmDummy
import com.hgh.na_o_man.domain.model.notification.DeletedCountModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AlarmContract {

    data class AlarmViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val alarmList: List<AlarmDummy> = listOf(),
    ): ViewState

    sealed class AlarmSideEffect : ViewSideEffect {
    }

    sealed class AlarmEvent : ViewEvent {
        object InitAlarmScreen : AlarmEvent()
        object OnReadAllClicked : AlarmEvent()
        object OnDeleteAllClicked : AlarmEvent()
        object OnPagingAlarmList : AlarmEvent()
        data class OnAlarmListClicked(val notificationID : Long) : AlarmEvent()
    }
}