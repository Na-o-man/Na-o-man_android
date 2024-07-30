package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class GroupDetailFolderContract {

    data class GroupDetailFolderViewState(
        val loadState: LoadState = LoadState.SUCCESS
    ) : ViewState

    sealed class GroupDetailFolderSideEffect : ViewSideEffect{

    }

    sealed class GroupDetailFolderEvent : ViewEvent{
        object InitGroupDetailFolderScreen : GroupDetailFolderEvent()
    }
}