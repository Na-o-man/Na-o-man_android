package com.hgh.na_o_man.presentation.ui.main

import androidx.compose.ui.graphics.Color
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState
import com.hgh.na_o_man.presentation.theme.BackgroundColor1

class AppContract {

    data class AppViewState(
        val statusBarColor: Color = BackgroundColor1,
    ) : ViewState

    sealed class AppSideEffect : ViewSideEffect {

    }

    sealed class AppEvent : ViewEvent {
        data class OnPlanItemClicked(val planId: Int) : AppEvent()
        object OnBottomNavigationClickedWhenNotLogin : AppEvent()
        object OnBottomSheetExitClicked : AppEvent()
        data class GetUserPlanStatus(val planId: Long): AppEvent()
        data class ChangeStatusBarColor(val color: Color): AppEvent()
    }

    enum class LoginState {
        NONE, LOGIN
    }
}
