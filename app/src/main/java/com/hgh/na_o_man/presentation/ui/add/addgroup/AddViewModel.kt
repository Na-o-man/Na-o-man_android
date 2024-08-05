package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddEvent
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect.NavigateToNextScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect.ShowToast
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
) : BaseViewModel<AddViewState, AddSideEffect, AddEvent>(
    AddViewState() // 초기 상태 설정
) {

    init {
        Log.d("리컴포저블", "AddViewModel")
    }

    override fun handleEvents(event: AddEvent) {
        when (event) {
            is AddEvent.NextButtonClicked -> {
                // 다음 버튼 클릭 처리 로직
                sendEffect ({ NavigateToNextScreen })
            }
            is AddEvent.TextInput -> {
            }
        }
    }

    fun onNextButtonClick(selectedButtons: List<Boolean> = emptyList()) {
        viewModelScope.launch {
            val selectedButtonCount = selectedButtons.count { it }
            if (selectedButtonCount > 0) {
                // 다음 버튼 클릭 이벤트 발생
                setEvent(AddEvent.NextButtonClicked(selectedButtonCount))
            } else {
                // 선택된 버튼이 없을 경우 사용자에게 알림
                sendEffect ({ ShowToast("최소 하나의 옵션을 선택해주세요") })
            }
        }
    }

    fun onTextInput(text: String) {
        // 입력 값에 따라 버튼 클릭 이벤트 처리
        setEvent(AddEvent.TextInput(text))
    }
}
