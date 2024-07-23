package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class AddViewModel @Inject constructor(
) : BaseViewModel<AddContract.AddViewState, AddSideEffect, AddContract.AddEvent>(
    AddContract.AddViewState() // 초기 상태 설정
) {

    init {
        Log.d("리컴포저블", "AddViewModel")
    }

    override fun handleEvents(event: AddContract.AddEvent) {
        when (event) {
            is AddContract.AddEvent.InitMembersInviteScreen -> {
                // 초기화 로직을 작성합니다.
                // 필요한 데이터를 로드하거나 상태를 업데이트 할 수 있습니다.
            }
            is AddContract.AddEvent.OnBackButtonClicked -> {
                // 뒤로가기 클릭 시 사이드 이펙트를 전송합니다.
                sendEffect({ AddContract.AddSideEffect.NavigateToMembersInviteScreen }) // 람다로 감싸기
            }
        }
    }

}
