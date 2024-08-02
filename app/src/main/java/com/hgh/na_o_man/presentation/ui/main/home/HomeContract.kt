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
    ): ViewState

    sealed class HomeSideEffect : ViewSideEffect {
        // "공유그룹 추가하기" 버튼 눌렀을 때 이벤트
        object NaviMembersInviteScreen : HomeSideEffect()

        // "공유그룹 입장" 버튼 눌렀을 때 이벤트
        object NaviAcceptInviteScreen : HomeSideEffect()
        data class NaviGroupDetail(val id : Long) : HomeSideEffect()
    }

    sealed class HomeEvent : ViewEvent {
        object InitHomeScreen : HomeEvent()
        object OnAddGroupInBoxClicked : HomeEvent()
        object OnAddGroupClicked : HomeEvent()
        object OnEnterGroupClicked : HomeEvent()
        data class OnGroupListClicked(val id : Long) : HomeEvent()
    }
}