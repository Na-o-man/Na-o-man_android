package com.hgh.na_o_man.presentation.ui.detail.vote

import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel
import com.hgh.na_o_man.domain.model.share_group.ShareGroupNameInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class VoteMainContract {

    data class VoteMainViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupId: Long = 0L,
        val voteList: List<VoteDummy> = listOf(),
        val voteDetail: AgendaInfoListModel? =null,
        val groupName: String = "",
        val groupNameList : List<ShareGroupNameInfoModel> = listOf()
    ) : ViewState

    sealed class VoteMainSideEffect : ViewSideEffect {

    }

    sealed class VoteMainEvent : ViewEvent {
        object InitVoteMainScreen : VoteMainEvent()
        object onAddAgendaInBoxClicked : VoteMainEvent()
    }
}