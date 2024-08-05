package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddEvent
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class JoinViewModel @Inject constructor(
) : BaseViewModel<JoinContract.JoinViewState, JoinContract.JoinSideEffect, JoinContract.JoinEvent>(
    JoinContract.JoinViewState() // 초기 상태 설정
) {
    private val membersList = mutableListOf<String>() // 멤버 리스트

    init {
        Log.d("리컴포저블", "JoinViewModel")
    }

    override fun handleEvents(event: JoinContract.JoinEvent) {
        when (event) {
            is JoinContract.JoinEvent.ChangeNickname -> {
            }
        }
    }
}