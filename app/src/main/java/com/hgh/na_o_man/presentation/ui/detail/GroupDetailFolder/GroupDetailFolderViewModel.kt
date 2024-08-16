package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.di.util.work_manager.enqueue.UploadEnqueuer
import com.hgh.na_o_man.domain.usecase.share_group.CheckSpecificGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    }

    override fun handleEvents(event: GroupDetailFolderContract.GroupDetailFolderEvent) {
        when (event) {
            is GroupDetailFolderContract.GroupDetailFolderEvent.InitGroupDetailFolderScreen -> {

            }

            is GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked -> {
                sendEffect({
                    GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoList(
                        viewState.value.groupId,
                        event.profileId,
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

            else -> {}
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
        fetchGroupDetail(id)
    }

    private fun fetchGroupDetail(groupId: Long) = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("GroupDetailViewModel", "fetchGroupDetail: Loading started for groupId: $groupId")

        try {
            Log.d("GroupDetailViewModel", "Calling checkSpecificGroupUsecase for groupId: $groupId")
            checkSpecificGroupUsecase(groupId).collect { result ->
                Log.d("GroupDetailViewModel", "Result received: $result")

                result.onSuccess { group ->
                    Log.d("GroupDetailViewModel", "Successfully fetched group detail: $group")
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            groupDetail = group
                        )
                    }
                    Log.d("GroupDetailViewModel", "State updated to SUCCESS")
                }.onFail { error ->
                    Log.d("GroupDetailViewModel", "Failed to fetch group detail: $error")
                    updateState {
                        copy(loadState = LoadState.ERROR)
                    }
                    Log.e("GroupDetailViewModel", "Error fetching group detail")
                }
            }
        } catch (e: Exception) {
            Log.e("GroupDetailViewModel", "Exception occurred while fetching group detail", e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }
}