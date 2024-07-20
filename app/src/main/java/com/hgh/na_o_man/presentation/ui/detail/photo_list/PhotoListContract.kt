package com.hgh.na_o_man.presentation.ui.detail.photo_list

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class PhotoListContract {

    data class PhotoListViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val isSelectMode : Boolean = false,
        val isDialogVisible: Boolean = false,
        val dialogPhoto: Dummy = Dummy(),
        val photoList : List<Dummy> = listOf(),
        val selectPhotoList : List<Dummy> = listOf(),
        val memberList : List<Dummy> = listOf(),
    ): ViewState

    sealed class PhotoListSideEffect : ViewSideEffect {
        object NaviBack : PhotoListSideEffect()
        object NaviVote : PhotoListSideEffect()
        data class ShowToast(val msg: String) : PhotoListSideEffect()
    }

    sealed class PhotoListEvent : ViewEvent {
        object InitPhotoListScreen : PhotoListEvent()
        object OnBackClicked : PhotoListEvent()
        object OnSelectModeClicked : PhotoListEvent()
        object OnVoteClicked : PhotoListEvent()
        object OnDownloadClicked : PhotoListEvent()
        object OnDeleteClicked : PhotoListEvent()
        data class OnImageClicked(val photo : Dummy) : PhotoListEvent()
        data class OnImageSelected(val photo: Dummy) : PhotoListEvent()
        object OnDialogClosed : PhotoListEvent()
    }
}