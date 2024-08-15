package com.hgh.na_o_man.presentation.ui.detail.agenda

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAgendaViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
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

            }

            AddAgendaContract.AddAgendaEvent.OnAddPhotosClicked -> {
                sendEffect({ AddAgendaContract.AddAgendaSideEffect.NaviPhotoList })
            }

            AddAgendaContract.AddAgendaEvent.OnBackClicked -> {
                sendEffect({ AddAgendaContract.AddAgendaSideEffect.NaviBack })
            }

            AddAgendaContract.AddAgendaEvent.OnDialogClosed -> {

            }
        }
    }
}
