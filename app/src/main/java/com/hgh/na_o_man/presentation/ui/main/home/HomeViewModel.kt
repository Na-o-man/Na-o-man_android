package com.hgh.na_o_man.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState(
        groupList = listOf(
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제",
                participantCount = 5,
                date = "2024-07-20"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            ),
            GroupDummy(
                imageRes = R.drawable.ic_example,
                name = "예제 2",
                participantCount = 3,
                date = "2024-07-21"
            )
        )
    )
) {
    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.InitHomeScreen -> {
                getGroupList()
            }
            is HomeContract.HomeEvent.OnAddGroupInBoxClicked -> {

            }
            is HomeContract.HomeEvent.OnAddGroupClicked -> {

            }
            is HomeContract.HomeEvent.onEnterGroupClicked -> {

            }
        }
    }

    init {
        Log.d("리컴포저블","HomeViewModelInit")
    }

    private fun getGroupList() = viewModelScope.launch {

    }
}