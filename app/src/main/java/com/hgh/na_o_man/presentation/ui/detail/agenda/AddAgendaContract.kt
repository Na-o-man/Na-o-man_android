package com.hgh.na_o_man.presentation.ui.detail.agenda

import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddAgendaContract {

    data class AddAgendaViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupId: Long = 0,
        val isDialogVisibility: Boolean = false,
        val agendaTitle : String = "",
    ) : ViewState

    sealed class AddAgendaSideEffect : ViewSideEffect {
        object NaviBack : AddAgendaSideEffect()
        object NaviPhotoList : AddAgendaSideEffect()
        data class ShowToast(val msg: String) : AddAgendaSideEffect()
    }

    sealed class AddAgendaEvent : ViewEvent {
        object InitAddAgendaScreen : AddAgendaEvent()
        object OnBackClicked : AddAgendaEvent()
        data class OnAddPhotosClicked(val title: String) : AddAgendaEvent()
        data class OnAddAgendaClicked(val title: String, val photos: List<PhotoInfoModel>): AddAgendaEvent()
        object OnDialogOpened : AddAgendaEvent()
        object OnDialogClosed : AddAgendaEvent()
    }
}