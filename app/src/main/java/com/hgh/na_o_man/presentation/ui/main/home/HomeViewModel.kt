package com.hgh.na_o_man.presentation.ui.main.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.domain.usecase.share_group.GroupListReferUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val groupListReferUsecase: GroupListReferUsecase,
    private val application: Application
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState()
) {
    init {
        Log.d("리컴포저블","HomeViewModelInit")
        setEvent(HomeContract.HomeEvent.InitHomeScreen)
    }

    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.InitHomeScreen -> {
                Log.d("HomeViewModel", "InitHomeScreen event")
                showGroupList()
            }
            is HomeContract.HomeEvent.OnAddGroupInBoxClicked -> {
                Log.d("HomeViewModel", "OnAddGroupInBoxClicked event")
                sendEffect(
                    { HomeContract.HomeSideEffect.NaviMembersInviteScreen }
                )
            }
            is HomeContract.HomeEvent.OnAddGroupClicked -> {

            }
            is HomeContract.HomeEvent.OnEnterGroupClicked -> {

            }
            is HomeContract.HomeEvent.OnGroupListClicked -> {
                Log.d("HomeViewModel","send id start")
                sendEffect(
                    { HomeContract.HomeSideEffect.NaviGroupDetail(event.id)}
                )
                Log.d("HomeViewModel","send id end")
            }
            else -> {}
        }
    }


    private fun showGroupList() = viewModelScope.launch {
        updateState { copy(loadState = LoadState.LOADING) }
        Log.d("HomeViewModel", "showGroupList: Loading started")
        try {
            Log.d("HomeViewModel", "Calling groupListReferUsecase")
            groupListReferUsecase(0, 20).collect { result ->
                Log.d("HomeViewModel","Result received $result")
                result.onSuccess { groupListReferModel ->
                    Log.d("HomeViewModel", "Successfully fetched group list: ${groupListReferModel.shareGroupInfoList}")
                    val groupList = groupListReferModel.shareGroupInfoList.map { groupInfo ->
                        val imageResID = getImageResId(groupInfo.image)
                        GroupDummy(
                            imageRes = imageResID,  //수정 필요
                            name = groupInfo.name,
                            participantCount = groupInfo.memberCount,
                            date = groupInfo.createdAt,
                            groupId = groupInfo.shareGroupId
                        )
                    }
                    updateState {
                        Log.d("HomeViewModel", "Updating state to SUCCESS")
                        copy(
                            loadState = LoadState.SUCCESS,
                            groupList = groupList
                        )
                    }
                    Log.d("HomeViewModel","Success fetch group list")
                }.onFail {
                    Log.d("HomeViewModel","Failed to fetch group list")
                    updateState {
                        Log.d("HomeViewModel", "Updating state to ERROR")
                        copy(loadState = LoadState.ERROR) }
                }
            }
        } catch (e: Exception){
            Log.d("HomeViewModel","Error loading group list",e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private fun getImageResId(imageName: String?): Int{
        return if(imageName.isNullOrEmpty()){
            R.drawable.ic_add_group_avatar_94
        } else {
            val resId = application.resources.getIdentifier(imageName, "drawable",application.packageName)
            if(resId != 0) resId else R.drawable.ic_add_group_avatar_94
        }
    }
}

