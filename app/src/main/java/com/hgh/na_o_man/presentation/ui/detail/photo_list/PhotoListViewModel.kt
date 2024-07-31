package com.hgh.na_o_man.presentation.ui.detail.photo_list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,

) : BaseViewModel<PhotoListContract.PhotoListViewState, PhotoListContract.PhotoListSideEffect, PhotoListContract.PhotoListEvent>(
    PhotoListContract.PhotoListViewState()
) {

    //private var memberId: Long = savedStateHandle.get<Long>(MEMBER_ID) ?: -1L

    init {
        Log.d("리컴포저블", "PhotoListViewModel")
        updateState {
            copy(
                loadState = LoadState.SUCCESS, photoList = listOf(
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        1,
                        is2 = true,
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        2,
                        is2 = true,
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        3,
                        is2 = true,
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        4
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        5
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        6
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        7
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        8
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        9
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        10
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        11
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        12
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        13
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        14
                    ),
                    Dummy(
                        "https://www.allurekorea.com/wp_data/allure/2024/04/style_662f367bb36c2-700x500.jpg?ver=5.0.21",
                        15
                    ),
                )
            )
        }
    }

    override fun handleEvents(event: PhotoListContract.PhotoListEvent) {
        when (event) {
            is PhotoListContract.PhotoListEvent.InitPhotoListScreen -> {

            }

            PhotoListContract.PhotoListEvent.OnBackClicked -> {

            }

            PhotoListContract.PhotoListEvent.OnDeleteClicked -> {

            }

            PhotoListContract.PhotoListEvent.OnDownloadClicked -> {

            }

            is PhotoListContract.PhotoListEvent.OnImageClicked -> {
                updateState { copy(dialogPhoto = event.photo.copy(is2 = false)) }
                updateState { copy(isDialogVisible = true) }
            }

            is PhotoListContract.PhotoListEvent.OnImageSelected -> {
                val selectedPhoto =
                    viewState.value.photoList.firstOrNull { it == event.photo }
                selectedPhoto?.let { photo ->
                    updateState {
                        copy(photoList = photoList.map {
                            if (it == photo) {
                                it.copy(is1 = !it.is1)
                            } else {
                                it
                            }
                        })
                    }
                }
            }

            PhotoListContract.PhotoListEvent.OnSelectModeClicked -> {
                updateState { copy(photoList = photoList.map { it.copy(is1 = false) }) }
                updateState { copy(isSelectMode = !isSelectMode) }
            }

            PhotoListContract.PhotoListEvent.OnVoteClicked -> {

            }

            PhotoListContract.PhotoListEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }
        }
    }
}