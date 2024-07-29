package com.hgh.na_o_man.presentation.ui.main.home

import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class HomeContract {

    data class HomeViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val groupList: List<GroupDummy> = listOf(),
        val BottomAddSelected: Boolean = false,
        val AddGroupInBoxSelected: Boolean = false,
        val AddGroupSelected: Boolean = false,
        val EnterGroupSelected: Boolean = false,

    ): ViewState

    sealed class HomeSideEffect : ViewSideEffect {
        object NaviHomeScreenWithButton : HomeSideEffect()
        object NaviAddSharedGroup : HomeSideEffect()
    }

    sealed class HomeEvent : ViewEvent {
        object InitHomeScreen : HomeEvent()
        object OnAddGroupInBoxClicked : HomeEvent()
        object OnAddGroupClicked : HomeEvent()
        object onEnterGroupClicked : HomeEvent()
    }
}