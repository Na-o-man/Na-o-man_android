package com.hgh.na_o_man.presentation.ui.main.mypage

import android.util.Log
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.di.util.remote.onError
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.member.GetMyInfoUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.sign.signin.SignContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val getMyInfoUsecase: GetMyInfoUsecase,
) : BaseViewModel<MyPageContract.MyPageViewState, MyPageContract.MyPageSideEffect, MyPageContract.MyPageEvent>(
    MyPageContract.MyPageViewState()
) {

    init {
        Log.d("리컴포저블", "MyPageViewModel")
    }

    override fun handleEvents(event: MyPageContract.MyPageEvent) {
        when (event) {
            is MyPageContract.MyPageEvent.InitMyPageScreen -> {
                getMyInfo()
            }

            MyPageContract.MyPageEvent.OnClickBack -> {
                sendEffect({ MyPageContract.MyPageSideEffect.NaviBack })
            }

            MyPageContract.MyPageEvent.OnClickLogOut -> {
                updateState { copy(dialogMod = DialogMode.LOGOUT, isDialogVisible = true) }
            }

            MyPageContract.MyPageEvent.OnClickSignOut -> {
                updateState { copy(dialogMod = DialogMode.SING_OUT, isDialogVisible = true) }
            }

            MyPageContract.MyPageEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }

            MyPageContract.MyPageEvent.OnClickDialogLogOut -> {
                authDataClear()
                sendEffect({ MyPageContract.MyPageSideEffect.LogOut })
            }

            MyPageContract.MyPageEvent.OnClickDialogSignOut -> {

            }
        }
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            updateState { copy(loadState = LoadState.LOADING) }
            try {
                getMyInfoUsecase().collect { result ->
                    result.onSuccess {
                        updateState { copy(userInfo = it, loadState = LoadState.SUCCESS) }
                    }.onFail {
                        updateState { copy( loadState = LoadState.ERROR) }
                    }.onException {
                        throw it
                    }
                }
            } catch (e: Exception) {
                Log.e("예외받기", "$e")
            }
        }
    }

    private fun authDataClear() {
        viewModelScope.launch {
            dataStoreUtil.clearDataStore()
        }
    }
}
