package com.hgh.na_o_man.presentation.ui.detail.vote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.domain.usecase.agenda.AgendaInfoListUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteMainViewModel @Inject constructor(
    private val agendaInfoListUsecase: AgendaInfoListUsecase,
    private val savedStateHandle: SavedStateHandle,

    ) : BaseViewModel<VoteMainContract.VoteMainViewState,VoteMainContract.VoteMainSideEffect,VoteMainContract.VoteMainEvent>(
    VoteMainContract.VoteMainViewState()
) {
    init {
        Log.d("리컴포저블","VoteMainViewModelInit")
        setEvent(VoteMainContract.VoteMainEvent.InitVoteMainScreen)
    }

    private val groupId: Long
        get() = savedStateHandle[KEY_GROUP_ID] ?: 0L


    override fun handleEvents(event: VoteMainContract.VoteMainEvent) {
        when(event) {
            is VoteMainContract.VoteMainEvent.InitVoteMainScreen -> {
                Log.d("VoteMainViewModel","InitVoteMainScreen event")
                showVoteList()
            }
            is VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked -> {

            }

            else -> {}
        }
    }

    private fun showVoteList() = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        try {
            agendaInfoListUsecase(groupId,0,20).collect() { result ->
                Log.d("VoteMainViewModel id확인", "$groupId")
                result.onSuccess { AgendaInfoListModel ->
                    val voteList = AgendaInfoListModel.agendaDetailInfoList.map { voteInfo ->
                        VoteDummy(
                            id = voteInfo.agendaId,
                            title = voteInfo.title,
                            images = voteInfo.agendaPhotoInfoList.map { it.agendaPhotoId },
                        )
                    }
                    updateState {
                        copy(
                            loadState = LoadState.SUCCESS,
                            voteList = voteList
                        )
                    }
                }.onFail {
                    updateState {
                        copy( loadState = LoadState.ERROR)
                    }
                }
            }
        } catch (e : Exception) {
            Log.e("VoteMainViewModel", "Error in showVoteList", e)
            updateState { copy( loadState = LoadState.ERROR) }
        }
    }
}