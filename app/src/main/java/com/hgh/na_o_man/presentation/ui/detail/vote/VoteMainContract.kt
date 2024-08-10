package com.hgh.na_o_man.presentation.ui.detail.vote

import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class VoteMainContract {

    data class VoteMainViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val voteList : List<VoteDummy> = listOf()
    ) : ViewState

    sealed class VoteMainSideEffect : ViewSideEffect {

    }

    sealed class VoteMainEvent : ViewEvent {
        object InitVoteMainScreen : VoteMainEvent()
        object onAddAgendaInBoxClicked : VoteMainEvent()

    }
}