package com.hgh.na_o_man.presentation.ui.main.alarm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.AlarmDummy
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
) : BaseViewModel<AlarmContract.AlarmViewState, AlarmContract.AlarmSideEffect, AlarmContract.AlarmEvent>(
    AlarmContract.AlarmViewState(
        alarmList = listOf(
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "개구쟁이 친구들과 발리 한마 : 해변 히어로즈]에 김봉순 님이 n장 사진을 업로드 했습@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@니다.",
                date = "2024-07-20"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            ),
            AlarmDummy(
                imageRes = R.drawable.ic_example,
                detail = "예제 2",
                date = "2024-07-21"
            )
        )
    )
) {
    override fun handleEvents(event: AlarmContract.AlarmEvent) {
        when (event) {
            is AlarmContract.AlarmEvent.InitAlarmScreen -> {

            }
        }
    }
        init {
            Log.d("리컴포저블","AlarmViewModel")
        }
}