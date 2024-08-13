package com.hgh.na_o_man.presentation.ui.detail.photo_list

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class PhotoListContract {

    data class PhotoListViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val isSelectMode: Boolean = false,
        val isDialogVisible: Boolean = false,
        val isAgenda: Boolean = false,
        val memberId: Long = 0,
        val dialogPhoto: PhotoInfoModel = PhotoInfoModel(),
        val photoList: List<PhotoInfoModel> = listOf(),
        val selectPhotoList: List<PhotoInfoModel> = listOf(),
        val memberList: List<ProfileInfoModel> = listOf(
            ProfileInfoModel(
                name = "전체",
                image = "",
                memberId = 100L,
                profileId = 100L
            ),
            ProfileInfoModel(
                name = "기타",
                image = "",
                memberId = 101L,
                profileId = 101L
            )
        ),
    ) : ViewState

    sealed class PhotoListSideEffect : ViewSideEffect {
        object NaviBack : PhotoListSideEffect()
        object NaviAgenda : PhotoListSideEffect()
        data class ShowToast(val msg: String) : PhotoListSideEffect()
    }

    sealed class PhotoListEvent : ViewEvent {
        object InitPhotoListScreen : PhotoListEvent()
        object OnBackClicked : PhotoListEvent()
        object OnSelectModeClicked : PhotoListEvent()
        object OnDownloadClicked : PhotoListEvent()
        object OnDeleteClicked : PhotoListEvent()
        object OnAgendaClicked : PhotoListEvent()
        data class OnImageClicked(val photo: PhotoInfoModel) : PhotoListEvent()
        data class OnImageSelected(val photo: PhotoInfoModel) : PhotoListEvent()
        object OnDialogClosed : PhotoListEvent()
        object OnPagingPhoto : PhotoListEvent()
        data class OnClickDropBoxItem(val member: ProfileInfoModel) : PhotoListEvent()
    }
}