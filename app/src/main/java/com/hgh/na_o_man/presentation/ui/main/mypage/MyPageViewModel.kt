package com.hgh.na_o_man.presentation.ui.main.mypage

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
) : BaseViewModel<MyPageContract.MyPageViewState, MyPageContract.MyPageSideEffect, MyPageContract.MyPageEvent>(
    MyPageContract.MyPageViewState()
) {

    init {
        Log.d("리컴포저블","MyPageViewModel")
    }
    override fun handleEvents(event: MyPageContract.MyPageEvent) {
        when (event) {
            is MyPageContract.MyPageEvent.InitMyPageScreen -> {

            }
        }
    }
}