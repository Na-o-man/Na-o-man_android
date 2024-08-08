package com.hgh.na_o_man.presentation.ui.detail.vote_detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.usecase.member.GetMyInfoUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    private val getMyInfoUsecase: GetMyInfoUsecase,
) : BaseViewModel<VoteDetailContract.VoteDetailViewState, VoteDetailContract.VoteDetailSideEffect, VoteDetailContract.VoteDetailEvent>(
    VoteDetailContract.VoteDetailViewState()
) {
    init {
        Log.d("리컴포저블", "VoteDetailViewModel")
    }

    override fun handleEvents(event: VoteDetailContract.VoteDetailEvent) {
        when (event) {
            VoteDetailContract.VoteDetailEvent.InitVoteDetailScreen -> {

            }

            VoteDetailContract.VoteDetailEvent.OnClickFinish -> {

            }

            VoteDetailContract.VoteDetailEvent.OnCLickBack -> {
                sendEffect({ VoteDetailContract.VoteDetailSideEffect.NaviBack })
            }

            VoteDetailContract.VoteDetailEvent.OnClickVote -> {
                updateState { copy(isVoteMode = true) }
                getMyInfo()
            }

            is VoteDetailContract.VoteDetailEvent.OnCLickNotVoteModeImage -> {
                updateState { copy(isDialogVisible = true, clickPhoto = event.photo) }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickVoteModeImage -> {
                updateState { copy(isDialogVisible = true, clickPhoto = event.photo) }
            }

            VoteDetailContract.VoteDetailEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }

            VoteDetailContract.VoteDetailEvent.OnClickBackOnVote -> {
                updateState {
                    copy(photos = photos.map {
                        if (it.is3) it.copy(is3 = false) else it
                    }, isVoteMode = false)
                }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickInject -> {
                updateState {
                    copy(
                        photos = photos.map {
                            if (it.id == event.photoId.toInt()) {
                                it.copy(is3 = true, dummyString3 = event.text)
                            } else {
                                it
                            }
                        },
                        isDialogVisible = false
                    )
                }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickCancelVote -> {
                updateState {
                    copy(
                        photos = photos.map {
                            if (it.id == event.photoId.toInt()) {
                                it.copy(is3 = false, dummyString3 = "")
                            } else {
                                it
                            }
                        },
                    )
                }
            }
        }
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            try {
                getMyInfoUsecase().collect { result ->
                    result.onSuccess {
                        updateState { copy(userInfo = it) }
                    }.onFail {
                        updateState { copy(loadState = LoadState.ERROR) }
                    }.onException {
                        throw it
                    }
                }
            } catch (e: Exception) {
                Log.e("예외받기", "$e")
            }
        }
    }
}
