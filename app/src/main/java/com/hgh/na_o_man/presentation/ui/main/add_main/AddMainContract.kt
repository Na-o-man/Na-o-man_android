package com.hgh.na_o_man.presentation.ui.main.add_main

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddMainContract {

    data class AddMainViewState(
        val loadState: LoadState = LoadState.SUCCESS,
        val haveSample : Boolean = false
    ) : ViewState

    sealed class AddMainSideEffect : ViewSideEffect {
        object NaviBack : AddMainSideEffect()
        object NaviJoin : AddMainSideEffect()
        object NaviAdd : AddMainSideEffect()
        object NaviSampleUpload : AddMainSideEffect()
        data class ShowToast(val msg: String) : AddMainSideEffect()

    }

    sealed class AddMainEvent : ViewEvent {
        object OnClickBack : AddMainEvent()
        object OnClickJoin : AddMainEvent()
        object OnClickAdd : AddMainEvent()
    }
}

enum class DialogMode(val title: String) {
    SING_OUT("탈퇴하시겠습니끼?\n탈퇴 시 데이터는 복구할 수 없습니다."),
    LOGOUT("로그아웃 하시겠습니까?"),
}