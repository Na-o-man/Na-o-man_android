package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.net.Uri
import com.hgh.na_o_man.domain.model.share_group.CheckSpecificGroupModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class GroupDetailFolderContract {

    data class GroupDetailFolderViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupId: Long = 0L,
        val uris: List<Uri> = listOf(),
        val groupDetail: CheckSpecificGroupModel? = null,
        val pagerIndex: Int = 0,
    ) : ViewState

    sealed class GroupDetailFolderSideEffect : ViewSideEffect {
        data class NaviPhotoList(val groupId: Long, val profiledId: Long, val memberId: Long) : GroupDetailFolderSideEffect()
        data class NaviVote(val groupId: Long) : GroupDetailFolderSideEffect()
        object NaviPhotoPicker : GroupDetailFolderSideEffect()
        data class ShowToast(val msg : String) : GroupDetailFolderSideEffect()
    }

    sealed class GroupDetailFolderEvent : ViewEvent {
        data class InitGroupDetailFolderScreen(val groupId: Long) : GroupDetailFolderEvent()
        object OnUploadClicked : GroupDetailFolderEvent()
        object OnVoteClicked : GroupDetailFolderEvent()
        data class OnUploadPhotoClicked(val uris: List<Uri>) : GroupDetailFolderEvent()
        data class OnUserFolderClicked(val profileId: Long, val memberId: Long, val currentPage: Int) : GroupDetailFolderEvent()
        data class OnDownloadClicked(val profileId: Long) : GroupDetailFolderEvent()
    }
}