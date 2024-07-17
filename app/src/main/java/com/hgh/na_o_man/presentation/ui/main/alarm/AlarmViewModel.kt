package com.hgh.na_o_man.presentation.ui.main.alarm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
) : BaseViewModel<AlarmContract.AlarmViewState, AlarmContract.AlarmSideEffect, AlarmContract.AlarmEvent>(
    AlarmContract.AlarmViewState()
) {

    init {
        Log.d("리컴포저블","AlarmViewModel")
    }
    override fun handleEvents(event: AlarmContract.AlarmEvent) {
        when (event) {
            is AlarmContract.AlarmEvent.InitAlarmScreen -> {

            }
        }
    }
}