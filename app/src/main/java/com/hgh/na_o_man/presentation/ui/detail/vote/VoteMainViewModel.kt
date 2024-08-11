package com.hgh.na_o_man.presentation.ui.detail.vote

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.domain.usecase.agenda.AgendaInfoListUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import kotlinx.coroutines.launch
import javax.inject.Inject

class VoteMainViewModel @Inject constructor(
    private val agendaInfoListUsecase: AgendaInfoListUsecase

) : BaseViewModel<VoteMainContract.VoteMainViewState,VoteMainContract.VoteMainSideEffect,VoteMainContract.VoteMainEvent>(
    VoteMainContract.VoteMainViewState()
) {
    init {
        Log.d("리컴포저블","VoteMainViewModelInit")
        setEvent(VoteMainContract.VoteMainEvent.InitVoteMainScreen)
    }
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

    fun initGroupId(id: Long) {
        updateState { copy(groupId = id) }
        Log.d("VoteMainViewModel id확인", "${viewState.value.groupId}")
    }

    private fun showVoteList() = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        try {
            agendaInfoListUsecase(viewState.value.groupId,0,20).collect() { result ->
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
            updateState { copy( loadState = LoadState.ERROR) }
        }
    }
}