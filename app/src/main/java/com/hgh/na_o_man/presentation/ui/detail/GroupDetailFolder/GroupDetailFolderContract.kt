package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class GroupDetailFolderContract {

    data class GroupDetailFolderViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupId : Long = 0L
    ) : ViewState

    sealed class GroupDetailFolderSideEffect : ViewSideEffect{
        data class NaviPhotoList(val groupId: Long, val memberId: Long) :  GroupDetailFolderSideEffect()
        data class NaviVote(val groupId: Long) : GroupDetailFolderSideEffect()
    }

    sealed class GroupDetailFolderEvent : ViewEvent{
        object InitGroupDetailFolderScreen : GroupDetailFolderEvent()
        object OnVoteClicked : GroupDetailFolderEvent()
        data class OnUserFolderClicked( val memberId: Long) :  GroupDetailFolderEvent()
    }
}