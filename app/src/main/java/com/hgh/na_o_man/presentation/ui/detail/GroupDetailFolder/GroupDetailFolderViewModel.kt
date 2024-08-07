package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.net.Uri
import android.util.Log
import com.hgh.na_o_man.di.util.work_manager.enqueue.UploadEnqueuer
import com.hgh.na_o_man.domain.usecase.share_group.CheckSpecificGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupDetailFolderViewModel @Inject constructor(
    private val checkSpecificGroupUsecase: CheckSpecificGroupUsecase,
    private val uploadEnqueuer: UploadEnqueuer
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

            GroupDetailFolderContract.GroupDetailFolderEvent.OnVoteClicked -> {
                sendEffect({
                    GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviVote(
                        viewState.value.groupId
                    )
                })
            }

            GroupDetailFolderContract.GroupDetailFolderEvent.OnUploadClicked -> {
                sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoPicker })
            }
        }
    }

    fun uploadUri(uris: List<Uri>) {
        updateState { copy(uris = uris) }
        uploadEnqueuer.enqueueUploadWork(
            viewState.value.groupId,
            viewState.value.uris.map { it.toString() })
    }

    fun initGroupId(id: Long) {
        updateState { copy(groupId = id) }
        Log.d("id확인", "${viewState.value.groupId}")
    }
}