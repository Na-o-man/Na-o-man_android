package com.hgh.na_o_man.presentation.ui.detail.vote

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import javax.inject.Inject

class VoteMainViewModel @Inject constructor(

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
}