package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.di.util.work_manager.enqueue.DownloadEnqueuer
import com.hgh.na_o_man.di.util.work_manager.enqueue.UploadEnqueuer
import com.hgh.na_o_man.domain.usecase.photo.PhotoAllAlbumUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoNoUsecase
import com.hgh.na_o_man.domain.usecase.share_group.CheckSpecificGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailFolderViewModel @Inject constructor(
    private val checkSpecificGroupUsecase: CheckSpecificGroupUsecase,
    private val downloadUserAlbumUsecase: PhotoAllAlbumUsecase,
    private val downloadEtcAlbumUsecase: PhotoNoUsecase,
    private val uploadEnqueuer: UploadEnqueuer,
    private val downloadEnqueuer: DownloadEnqueuer,
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
                initGroupId(event.groupId)
            }

            is GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked -> {
                updateState { copy(pagerIndex = event.currentPage) }
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

            is GroupDetailFolderContract.GroupDetailFolderEvent.OnDownloadClicked -> {
                when (event.profileId) {
                    100L -> {
                        sendEffect({
                            GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast("전체 사진은 다운로드 할 수 없습니다.")
                        })
                    }

                    101L -> {
                        downloadEtcAlbum()
                    }

                    else -> {
                        downloadUserAlbum(event.profileId)
                    }
                }
            }

            GroupDetailFolderContract.GroupDetailFolderEvent.OnUploadClicked -> {
                sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoPicker })
            }

            is GroupDetailFolderContract.GroupDetailFolderEvent.OnUploadPhotoClicked -> {
                uploadUri(event.uris)
            }
        }
    }

    private fun uploadUri(uris: List<Uri>) {
        updateState { copy(uris = uris) }
        uploadEnqueuer.enqueueUploadWork(
            viewState.value.groupId,
            viewState.value.uris.map { it.toString() })
    }

    private fun initGroupId(id: Long) {
        updateState { copy(groupId = id) }
        Log.d("id확인", "${viewState.value.groupId}")
        fetchGroupDetail(id)
    }

    private fun fetchGroupDetail(groupId: Long) = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        try {
            checkSpecificGroupUsecase(groupId).collect { result ->
                result.onSuccess { group ->
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

    private fun downloadUserAlbum(profileId: Long) = viewModelScope.launch {
        try {
            downloadUserAlbumUsecase(
                shareGroupId = viewState.value.groupId,
                profileId = profileId
            ).collect { result ->
                result.onSuccess {
                    if (it.downloadUrls.isNotEmpty()) {
                        downloadEnqueuer.enqueueDownloadWork(it.downloadUrls)
                    } else {
                        sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast("폴더에 사진이 없습니다.") })
                    }
                }.onFail {
                    sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun downloadEtcAlbum() = viewModelScope.launch {
        try {
            downloadEtcAlbumUsecase(
                shareGroupId = viewState.value.groupId
            ).collect { result ->
                result.onSuccess {
                    if (it.downloadUrls.isNotEmpty()) {
                        downloadEnqueuer.enqueueDownloadWork(it.downloadUrls)
                    } else {
                        sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast("폴더에 사진이 없습니다.") })
                    }
                }.onFail {
                    sendEffect({ GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }
}