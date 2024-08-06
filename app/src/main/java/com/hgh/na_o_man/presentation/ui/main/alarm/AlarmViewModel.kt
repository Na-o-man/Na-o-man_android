package com.hgh.na_o_man.presentation.ui.main.alarm

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.R
import com.hgh.na_o_man.data.dto.notification.response.DeletedCountDto
import com.hgh.na_o_man.data.dto.notification.response.UnreadDto
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.AlarmDummy
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.domain.usecase.notification.AcknowledgedCountUsecase
import com.hgh.na_o_man.domain.usecase.notification.DeleteAcknowledgedCountUsecase
import com.hgh.na_o_man.domain.usecase.notification.DeletedCountUsecase
import com.hgh.na_o_man.domain.usecase.notification.NotificationInfoListUsecase
import com.hgh.na_o_man.domain.usecase.notification.PostFcmUsecase
import com.hgh.na_o_man.domain.usecase.notification.UnreadNotificationUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val acknowledgedCountUsecase : AcknowledgedCountUsecase,
    private val deleteAcknowledgedCountUsecase: DeleteAcknowledgedCountUsecase,
    private val deletedCountUsecase : DeletedCountUsecase,
    private val notificationInfoListUsecase : NotificationInfoListUsecase,
    private val postFcmUsecase : PostFcmUsecase,
    private val unreadNotificationUsecase : UnreadNotificationUsecase

) : BaseViewModel<AlarmContract.AlarmViewState, AlarmContract.AlarmSideEffect, AlarmContract.AlarmEvent>(
    AlarmContract.AlarmViewState()
) {
    init {
        Log.d("리컴포저블","AlarmViewModel")
        setEvent(AlarmContract.AlarmEvent.InitAlarmScreen)
    }


    override fun handleEvents(event: AlarmContract.AlarmEvent) {
        when (event) {
            is AlarmContract.AlarmEvent.InitAlarmScreen -> {
                showAlarmList()
            }

            is AlarmContract.AlarmEvent.OnReadAllClicked -> {
                readAll()
            }

            is AlarmContract.AlarmEvent.OnDeleteAllClicked -> {
                deleteAll()
            }

            is AlarmContract.AlarmEvent.OnAlarmListClicked -> {

            }

        }
    }

    // 알람 리스트 구현
    private fun showAlarmList() = viewModelScope.launch{
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("AlarmViewModel", "showAlarmList: Loading started")
        try {
            notificationInfoListUsecase(0,20, listOf("date,desc")).collect{result ->
                result.onSuccess { notificationInfoListModel ->
                    Log.d("AlarmViewModel", "Successfully fetched alarm list")
                    val alarmList = notificationInfoListModel.notificationInfoList.map { notificationInfo ->
                        AlarmDummy(
                            url = notificationInfo.url,
                            detail = notificationInfo.body,
                            date = notificationInfo.createdAt,
                            imageRes = R.drawable.ic_example // 예시 이미지 리소스 ID --> 이거 알림인데 그냥 프사 넣을건데 왜 필요하지...??
                        )
                    }
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            alarmList = alarmList
                        )
                    }
                    Log.d("AlarmViewModel", "showAlarmList: State updated to SUCCESS with ${alarmList.size} items")
                }.onFail {
                    Log.d("AlarmViewModel", "Failed to fetch alarm list")
                    updateState { copy(loadState = LoadState.ERROR) }
                }
            }
        } catch (e: Exception) {
            Log.e("AlarmViewModel","Error loading alarm list",e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }


    // 모두 읽음 기능
    private fun readAll()  = viewModelScope.launch{
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("AlarmViewModel","readAll : Reading all alarms")
        try {
            unreadNotificationUsecase().collect{ result ->
                result.onSuccess {
                    Log.d("AlarmViewModel","Successfully read all alarms")
                    val updatedAlarmList = viewState.value.alarmList.map{
                        it.copy(isRead = true)
                    }
                    updateState { copy(
                        loadState = LoadState.SUCCESS,
                        alarmList = updatedAlarmList
                        )
                    }
                }.onFail {
                    Log.d("AlarmViewModel","Failed to read all alarms")
                    updateState { copy(loadState = LoadState.ERROR) }
                }
            }
        } catch (e:Exception) {
            Log.e("AlarmViewModel","Error reading all alarms",e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    // 전체 삭제 기능
    private fun deleteAll() = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("AlarmViewModel","deleteAll : Deleting all alarms")
        try {
            deletedCountUsecase().collect{ result ->
                result.onSuccess {
                    Log.d("AlarmViewModel","Successfully deleted all alarms")
                    updateState { copy(loadState = LoadState.SUCCESS, alarmList = emptyList()) }
                } .onFail {
                    Log.d("AlarmViewModel","Failed to delete all alarms")
                    updateState { copy(loadState = LoadState.ERROR) }
                }
            }

        } catch (e:Exception) {
            Log.e("AlarmViewModel","Error delete all alarms",e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }
}