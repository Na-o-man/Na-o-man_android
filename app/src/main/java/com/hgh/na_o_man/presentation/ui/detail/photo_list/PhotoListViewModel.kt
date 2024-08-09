package com.hgh.na_o_man.presentation.ui.detail.photo_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.photo.PhotoAllUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.detail.ALL_PHOTO_ID
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import com.hgh.na_o_man.presentation.ui.detail.KEY_IS_AGENDA
import com.hgh.na_o_man.presentation.ui.detail.KEY_MEMBER_ID
import com.hgh.na_o_man.presentation.ui.detail.OTHER_PHOTO_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotoAllUsecase: PhotoAllUsecase,
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
        getAllPhoto()

        Log.d("리컴포저블", "PhotoListViewModel")
        updateState {
            copy(
                loadState = LoadState.SUCCESS
            )
        }
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

            }

            PhotoListContract.PhotoListEvent.OnDownloadClicked -> {

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

            PhotoListContract.PhotoListEvent.OnReachBottom -> {
                if (viewState.value.memberId == ALL_PHOTO_ID) {
                    getAllPhoto()
                } else if (viewState.value.memberId == OTHER_PHOTO_ID) {

                } else {

                }
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

}