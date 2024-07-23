package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupDetailFolderViewModel @Inject constructor(
) : BaseViewModel<GroupDetailFolderContract.GroupDetailFolderViewState, GroupDetailFolderContract.GroupDetailFolderSideEffect, GroupDetailFolderContract.GroupDetailFolderEvent>(
    GroupDetailFolderContract.GroupDetailFolderViewState()
) {
    init {
        Log.d("리컴포저블", "GroupDetailFolderViewModel")
    }

    override fun handleEvents(event: GroupDetailFolderContract.GroupDetailFolderEvent) {
        when(event){
            is GroupDetailFolderContract.GroupDetailFolderEvent.InitGroupDetailFolderScreen -> {

            }
        }
    }
}