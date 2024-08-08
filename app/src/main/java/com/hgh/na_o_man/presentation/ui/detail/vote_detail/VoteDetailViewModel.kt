package com.hgh.na_o_man.presentation.ui.detail.vote_detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
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
            VoteDetailContract.VoteDetailEvent.OnClickVote -> {
                updateState { copy(isVoteMode = true) }
                getMyInfo()
            }

            is VoteDetailContract.VoteDetailEvent.OnCLickNotVoteModeImage -> {

            }
            is VoteDetailContract.VoteDetailEvent.OnClickVoteModeImage -> {
                updateState { copy(isDialogVisible = true, clickPhoto = event.photo) }
            }

            VoteDetailContract.VoteDetailEvent.OnClickInject -> {

            }

            VoteDetailContract.VoteDetailEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }
        }
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            updateState { copy(loadState = LoadState.LOADING) }
            try {
                getMyInfoUsecase().collect { result ->
                    result.onSuccess {
                        updateState { copy(userInfo = it) }
                    }.onFail {
                        updateState { copy( loadState = LoadState.ERROR) }
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
