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
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val nextPage = MutableStateFlow(0)
    private val hasNextPage = MutableStateFlow(true)

    init {
        Log.d("리컴포저블","AlarmViewModel")
        setEvent(AlarmContract.AlarmEvent.OnPagingAlarmList)
    }


    override fun handleEvents(event: AlarmContract.AlarmEvent) {
        when (event) {
            is AlarmContract.AlarmEvent.InitAlarmScreen -> {
            }

            is AlarmContract.AlarmEvent.OnReadAllClicked -> {
                Log.d("AlarmViewModel", "OnReadAllClicked 이벤트 처리")
                readAll()
            }

            is AlarmContract.AlarmEvent.OnDeleteAllClicked -> {
                Log.d("AlarmViewModel", "OnDeleteAllClicked 이벤트 처리")
                deleteAll()
            }

            is AlarmContract.AlarmEvent.OnAlarmListClicked -> {

            }
            AlarmContract.AlarmEvent.OnPagingAlarmList -> {
                showAlarmList()
            }

            else -> {}
        }
    }

    // 알람 리스트 구현
    private fun showAlarmList() = viewModelScope.launch{
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("AlarmViewModel", "showAlarmList: Loading started")
        try {
            if(hasNextPage.value) {
                notificationInfoListUsecase(nextPage.value, 10, listOf("date,desc")).collect { result ->
                    result.onSuccess { notificationInfoListModel ->
                        Log.d("AlarmViewModel", "Successfully fetched alarm list")
                        val alarmList =
                            notificationInfoListModel.notificationInfoList.map { notificationInfo ->
                                AlarmDummy(
                                    url = notificationInfo.url,
                                    detail = notificationInfo.body,
                                    date = notificationInfo.createdAt,
                                    //url 통해서 이미지 로드
                                    imageRes = R.drawable.ic_example // 예시 이미지 리소스 ID --> 이거 알림인데 그냥 프사 넣을건데 왜 필요하지...??
                                )
                            }
                        updateState {
                            copy(
                                loadState = LoadState.SUCCESS,
                                alarmList = alarmList
                            )
                        }
                        notificationInfoListModel.isLast.not().let {
                            hasNextPage.value = it
                            nextPage.value +=1
                        }
                        // 위에거 안되면 이거 하기
//                        if (response.last) {
//                            hasNextPage.value = false // 더 이상 페이지가 없으면 hasNextPage를 false로 설정
//                            Log.d("HomeViewModel", "Last page reached, no more loading.")
//                        } else {
//                            nextPage.value += 1
//                        }
                        Log.d("AlarmViewModel", "showAlarmList: State updated to SUCCESS with ${alarmList.size} items")
                    }.onFail {
                        Log.d("AlarmViewModel", "Failed to fetch alarm list")
                        updateState { copy(loadState = LoadState.ERROR) }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("AlarmViewModel","Error loading alarm list",e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private fun readAll() = viewModelScope.launch {
        Log.d("AlarmViewModel","readAll : Reading all alarms")
        try {
            acknowledgedCountUsecase().collect { result ->
                result.onSuccess {
                    Log.d("AlarmViewModel", "Successfully read all alarms")
                    val updatedAlarmList = viewState.value.alarmList.map {
                        it.copy(isRead = true)
                    }
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            alarmList = updatedAlarmList
                        )
                    }
                }.onFail {
                    Log.d("AlarmViewModel", "Failed to read all alarms")
                    updateState { copy(loadState = LoadState.ERROR) }
                }
            }
        } catch (e : Exception) {
            Log.e("AlarmViewModel", "Error reading all alarms", e)
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