package com.hgh.na_o_man.presentation.ui.add.addgroup

import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class AddContract {
    data class AddViewState(
        val selectedButtonCount: Int = 0, // 선택된 버튼 개수
        val textValue: String = ""
    ) : ViewState

    sealed class AddSideEffect : ViewSideEffect {
        object NavigateToNextScreen : AddSideEffect()
        data class ShowToast(val message: String) : AddSideEffect() // ShowToast 사이드 이펙트 추가
    }

    sealed class AddEvent : ViewEvent {
        data class NextButtonClicked(val selectedButtonCount: Int) : AddEvent() // 다음 버튼 클릭 이벤트
        data class TextInput(val text: String) : AddEvent() // 텍스트 입력 이벤트
    }
}
