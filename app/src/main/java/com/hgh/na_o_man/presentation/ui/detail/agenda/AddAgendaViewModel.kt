package com.hgh.na_o_man.presentation.ui.detail.agenda

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.agenda.request.AgendaRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.domain.usecase.agenda.AgendaCreateUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAgendaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createAgendaUsecase: AgendaCreateUsecase,
) : BaseViewModel<AddAgendaContract.AddAgendaViewState, AddAgendaContract.AddAgendaSideEffect, AddAgendaContract.AddAgendaEvent>(
    AddAgendaContract.AddAgendaViewState()
) {
    init {
        updateState {
            copy(
                groupId = savedStateHandle[KEY_GROUP_ID] ?: 0L
            )
        }
        Log.d("리컴포저블", "AddAgendaViewModel")
    }

    override fun handleEvents(event: AddAgendaContract.AddAgendaEvent) {
        when (event) {
            AddAgendaContract.AddAgendaEvent.InitAddAgendaScreen -> {

            }

            is AddAgendaContract.AddAgendaEvent.OnAddAgendaClicked -> {
                createAgenda(event.title, event.photos)
            }

            is AddAgendaContract.AddAgendaEvent.OnAddPhotosClicked -> {
                updateState { copy(agendaTitle = event.title) }
                sendEffect({ AddAgendaContract.AddAgendaSideEffect.NaviPhotoList })
            }

            AddAgendaContract.AddAgendaEvent.OnBackClicked -> {
                sendEffect({ AddAgendaContract.AddAgendaSideEffect.NaviBack })
            }

            AddAgendaContract.AddAgendaEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisibility = false) }
            }

            AddAgendaContract.AddAgendaEvent.OnDialogOpened -> {
                updateState { copy(isDialogVisibility = true) }
            }
        }
    }

    private fun createAgenda(title: String, photos: List<PhotoInfoModel>) = viewModelScope.launch {
        try {
            createAgendaUsecase(
                AgendaRequestDto(
                    title = title,
                    shareGroupId = viewState.value.groupId,
                    photoIdList = photos.map { it.photoId }
                )
            ).collect { result ->
                result.onSuccess {
                    sendEffect({ AddAgendaContract.AddAgendaSideEffect.NaviBack })
                }.onFail {
                    sendEffect({ AddAgendaContract.AddAgendaSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }
}
