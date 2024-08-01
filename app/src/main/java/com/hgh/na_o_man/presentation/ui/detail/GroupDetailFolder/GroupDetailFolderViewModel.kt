package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupDetailFolderViewModel @Inject constructor(
) : BaseViewModel<GroupDetailFolderContract.GroupDetailFolderViewState, GroupDetailFolderContract.GroupDetailFolderSideEffect, GroupDetailFolderContract.GroupDetailFolderEvent>(
    GroupDetailFolderContract.GroupDetailFolderViewState()
) {
    init {
        Log.d("리컴포저블", "GroupDetailFolderViewModel")
        updateState { copy(loadState = LoadState.LOADING) }

        updateState { copy(loadState = LoadState.SUCCESS) }
    }

    override fun handleEvents(event: GroupDetailFolderContract.GroupDetailFolderEvent) {
        when (event) {
            is GroupDetailFolderContract.GroupDetailFolderEvent.InitGroupDetailFolderScreen -> {

            }

            is GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked -> {
                sendEffect({
                    GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoList(
                        viewState.value.groupId,
                        event.memberId
                    )
                })
            }
        }
    }

    fun initGroupId(id: Long) {
        updateState { copy(groupId = id) }
        Log.d("id확인", "${viewState.value.groupId}")
    }
}