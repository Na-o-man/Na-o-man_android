package com.hgh.na_o_man.presentation.ui.detail.photo_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.photo.request.PhotoIdListDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.di.util.work_manager.enqueue.DownloadEnqueuer
import com.hgh.na_o_man.domain.usecase.member.GetMyIdUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoAllUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoDeleteUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoEtcUsecase
import com.hgh.na_o_man.domain.usecase.photo.PhotoUsecase
import com.hgh.na_o_man.domain.usecase.share_group.CheckSpecificGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.detail.ALL_PHOTO_ID
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import com.hgh.na_o_man.presentation.ui.detail.KEY_IS_AGENDA
import com.hgh.na_o_man.presentation.ui.detail.KEY_MEMBER_ID
import com.hgh.na_o_man.presentation.ui.detail.OTHER_PHOTO_ID
import com.hgh.na_o_man.presentation.ui.sign.signin.SignContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotoAllUsecase: PhotoAllUsecase,
    private val getMemberUsecase: CheckSpecificGroupUsecase,
    private val downloadEnqueuer: DownloadEnqueuer,
    private val getPhotoUsecase: PhotoUsecase,
    private val getPhotoEtcUsecase: PhotoEtcUsecase,
    private val deletePhotoUsecase: PhotoDeleteUsecase,
    private val getMyIdUsecase: GetMyIdUsecase,
) : BaseViewModel<PhotoListContract.PhotoListViewState, PhotoListContract.PhotoListSideEffect, PhotoListContract.PhotoListEvent>(
    PhotoListContract.PhotoListViewState()
) {
    private val groupId: Long
        get() = savedStateHandle[KEY_GROUP_ID] ?: 0L

    private val nextPage = MutableStateFlow(0)
    private val hasNextPage = MutableStateFlow(true)

    init {
        updateState {
            copy(
                isAgenda = savedStateHandle[KEY_IS_AGENDA] ?: false,
                memberId = savedStateHandle[KEY_MEMBER_ID] ?: 0L
            )
        }
        getMyId()
        setEvent(PhotoListContract.PhotoListEvent.OnPagingPhoto)
        getGroupMember()
        Log.d("리컴포저블", "PhotoListViewModel")
    }

    override fun handleEvents(event: PhotoListContract.PhotoListEvent) {
        when (event) {
            is PhotoListContract.PhotoListEvent.InitPhotoListScreen -> {
                Log.d("id확인", "${groupId},${viewState.value.memberId}")
            }

            PhotoListContract.PhotoListEvent.OnBackClicked -> {
                sendEffect({ PhotoListContract.PhotoListSideEffect.NaviBack })
            }

            PhotoListContract.PhotoListEvent.OnDeleteClicked -> {
                deletePhoto()
            }

            PhotoListContract.PhotoListEvent.OnDownloadClicked -> {
                downloadPhoto()
            }

            is PhotoListContract.PhotoListEvent.OnImageClicked -> {
                updateState { copy(dialogPhoto = event.photo.copy(isDownloaded = false)) }
                updateState { copy(isDialogVisible = true) }
            }

            is PhotoListContract.PhotoListEvent.OnImageSelected -> {
                val selectedPhoto =
                    viewState.value.photoList.firstOrNull { it == event.photo }
                selectedPhoto?.let { photo ->
                    updateState {
                        copy(photoList = photoList.map {
                            if (it == photo) {
                                it.copy(isSelected = !it.isSelected)
                            } else {
                                it
                            }
                        })
                    }
                }
            }

            PhotoListContract.PhotoListEvent.OnSelectModeClicked -> {
                updateState { copy(photoList = photoList.map { it.copy(isSelected = false) }) }
                updateState { copy(isSelectMode = !isSelectMode) }
            }

            PhotoListContract.PhotoListEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }

            PhotoListContract.PhotoListEvent.OnAgendaClicked -> {
                updateState { copy(selectPhotoList = photoList.filter { it.isSelected }) }
                sendEffect({ PhotoListContract.PhotoListSideEffect.NaviAgenda })
            }

            PhotoListContract.PhotoListEvent.OnPagingPhoto -> {
                if (viewState.value.memberId == ALL_PHOTO_ID) {
                    getAllPhoto()
                } else if (viewState.value.memberId == OTHER_PHOTO_ID) {
                    getEtcPhoto()
                } else {
                    getMemberPhoto()
                }
            }

            is PhotoListContract.PhotoListEvent.OnClickDropBoxItem -> {
                hasNextPage.value = true
                nextPage.value = 0
                updateState {
                    copy(
                        memberId = event.member.memberId,
                        photoList = listOf(),
                        isMine = viewState.value.memberId == viewState.value.myId
                    )
                }
                setEvent(PhotoListContract.PhotoListEvent.OnPagingPhoto)
            }
        }
    }

    private fun getAllPhoto() = viewModelScope.launch {
        try {
            if (hasNextPage.value) {
                getPhotoAllUsecase(groupId, nextPage.value, 14).collect { result ->
                    result.onSuccess { response ->
                        updateState {
                            copy(
                                photoList = viewState.value.photoList + response.photoInfoList,
                                loadState = LoadState.SUCCESS
                            )
                        }
                        response.isLast.not().let {
                            hasNextPage.value = it
                            nextPage.value += 1
                        }
                    }.onFail {
                        updateState { copy(loadState = LoadState.ERROR) }
                    }.onException {
                        throw it
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun getEtcPhoto() = viewModelScope.launch {
        try {
            if (hasNextPage.value) {
                getPhotoEtcUsecase(groupId, nextPage.value, 14).collect { result ->
                    result.onSuccess { response ->
                        updateState {
                            copy(
                                photoList = viewState.value.photoList + response.photoInfoList,
                                loadState = LoadState.SUCCESS
                            )
                        }
                        response.isLast.not().let {
                            hasNextPage.value = it
                            nextPage.value += 1
                        }
                    }.onFail {
                        updateState { copy(loadState = LoadState.ERROR) }
                    }.onException {
                        throw it
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }


    private fun getMemberPhoto() = viewModelScope.launch {
        try {
            if (hasNextPage.value) {
                getPhotoUsecase(
                    groupId,
                    viewState.value.memberId,
                    nextPage.value,
                    14
                ).collect { result ->
                    result.onSuccess { response ->
                        updateState {
                            copy(
                                photoList = viewState.value.photoList + response.photoInfoList,
                                loadState = LoadState.SUCCESS
                            )
                        }
                        response.isLast.not().let {
                            hasNextPage.value = it
                            nextPage.value += 1
                        }
                    }.onFail {
                        updateState { copy(loadState = LoadState.ERROR) }
                    }.onException {
                        throw it
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun getMyId() = viewModelScope.launch {
        try {
            getMyIdUsecase().collect { result ->
                result.onSuccess { response ->
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            myId = response.memberId
                        )
                    }
                }.onFail { error ->
                    updateState {
                        copy(loadState = LoadState.ERROR)
                    }
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun getGroupMember() = viewModelScope.launch {
        try {
            getMemberUsecase(groupId).collect { result ->
                result.onSuccess { response ->
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            memberList = response.profileInfoList + viewState.value.memberList
                        )
                    }
                }.onFail { error ->
                    updateState {
                        copy(loadState = LoadState.ERROR)
                    }
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun deletePhoto() = viewModelScope.launch {
        try {
            if (viewState.value.isDialogVisible) {
                deletePhotoUsecase(
                    PhotoIdListDto(
                        shareGroupId = groupId,
                        photoIdList = listOf(viewState.value.dialogPhoto.photoId)
                    )
                ).collect { result ->
                    result.onSuccess { response ->
                        updateState {
                            copy(
                                photoList = viewState.value.photoList.filterNot {
                                    it.photoId == viewState.value.dialogPhoto.photoId
                                },
                                isDialogVisible = false
                            )
                        }
                    }.onFail {
                        sendEffect({ PhotoListContract.PhotoListSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                    }.onException {
                        throw it
                    }
                }
            } else {
                deletePhotoUsecase(
                    PhotoIdListDto(
                        shareGroupId = groupId,
                        photoIdList = viewState.value.photoList
                            .filter { it.isSelected }
                            .map { it.photoId }
                    )
                ).collect { result ->
                    result.onSuccess { response ->
                        updateState {
                            copy(
                                photoList = viewState.value.photoList.filterNot {
                                    it.photoId in response.photoIdList
                                },
                                isDialogVisible = false
                            )
                        }
                    }.onFail {
                        sendEffect({ PhotoListContract.PhotoListSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                    }.onException {
                        throw it
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun downloadPhoto() = viewModelScope.launch {
        if (viewState.value.isDialogVisible) {
            downloadEnqueuer.enqueueDownloadWork(listOf(viewState.value.dialogPhoto.rawPhotoUrl))
            updateState { copy(isDialogVisible = false) }
        } else {
            downloadEnqueuer.enqueueDownloadWork(
                viewState.value.photoList
                    .filter { it.isSelected }
                    .map { it.rawPhotoUrl }
            )
            updateState {
                copy(photoList = photoList.map { photo ->
                    if (photo.isSelected) {
                        photo.copy(isDownloaded = true, isSelected = false)
                    } else {
                        photo
                    }
                })
            }
        }
    }
}
