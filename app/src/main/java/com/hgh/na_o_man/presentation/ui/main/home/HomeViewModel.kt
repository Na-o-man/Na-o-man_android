package com.hgh.na_o_man.presentation.ui.main.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.domain.usecase.share_group.GroupListReferUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val groupListReferUsecase: GroupListReferUsecase,
    private val application: Application
) : BaseViewModel<HomeContract.HomeViewState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>(
    HomeContract.HomeViewState()
) {

    private val nextPage = MutableStateFlow(0)
    private val hasNextPage = MutableStateFlow(true)

    init {
        Log.d("왜왜왜", "init")
        setEvent(HomeContract.HomeEvent.OnPagingGroupList)
    }

    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.InitHomeScreen -> {
                Log.d("왜왜", "InitHomeScreen")
                CoroutineScope(Dispatchers.IO).launch {
                    showRefreshGroupList()
                }
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
                Log.d("HomeViewModel", "send id start")
                sendEffect(
                    { HomeContract.HomeSideEffect.NaviGroupDetail(event.id) }
                )
                Log.d("HomeViewModel", "send id end")
            }

            HomeContract.HomeEvent.OnPagingGroupList -> {
                Log.d("왜왜", "OnPagingGroupList")
                showGroupList()
            }

            else -> {}
        }
    }


    private fun showGroupList() = viewModelScope.launch {
        try {
            if (hasNextPage.value) {
                groupListReferUsecase(nextPage.value, 10).collect { result ->
                    result.onSuccess { response ->
                        val newGroupList = response.shareGroupInfoList.map { groupInfo ->
//                            val imageResID = getImageResId(groupInfo.image)
                            GroupDummy(
                                imageRes = groupInfo.image?:"https://i.ibb.co/BKpgBf1/na-o-man-null-img.png",  //수정 필요
                                name = groupInfo.name,
                                participantCount = groupInfo.memberCount,
                                date = groupInfo.createdAt,
                                groupId = groupInfo.shareGroupId
                            )
                        }
                        updateState {
                            copy(
                                groupList = viewState.value.groupList + newGroupList,
                                loadState = LoadState.SUCCESS
                            )
                        }
                        response.last.not().let {
                            hasNextPage.value = it
                            nextPage.value += 1
                        }
                    }.onFail {
                        updateState {
                            copy(loadState = LoadState.ERROR)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("HomeViewModel", "Error loading group list", e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private suspend fun showRefreshGroupList() = viewModelScope.launch {
        try {
            Log.d("왜왜", "showRefreshGroupList")
            updateState { copy(groupList = listOf()) }
            groupListReferUsecase(0, 10).collect { result ->
                result.onSuccess { response ->
                    val newGroupList = response.shareGroupInfoList.map { groupInfo ->
//                        val imageResID = getImageResId(groupInfo.image)
                        GroupDummy(
                            imageRes = groupInfo.image?:"https://i.ibb.co/BKpgBf1/na-o-man-null-img.png",  //수정 필요
                            name = groupInfo.name,
                            participantCount = groupInfo.memberCount,
                            date = groupInfo.createdAt,
                            groupId = groupInfo.shareGroupId
                        )
                    }
                    updateState {
                        Log.d("HomeViewModel", "Updating state to SUCCESS")
                        copy(
                            groupList = viewState.value.groupList + newGroupList,
                            loadState = LoadState.SUCCESS
                        )
                    }
                    response.last.not().let {
                        hasNextPage.value = it
                        nextPage.value = 1
                    }
                }.onFail {
                    updateState {
                        copy(loadState = LoadState.ERROR)
                    }
                }

            }
        } catch (e: Exception) {
            Log.d("HomeViewModel", "Error loading group list", e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private fun getImageResId(imageName: String?): String {
        return if (imageName.isNullOrEmpty()) {
            "https://i.ibb.co/BKpgBf1/na-o-man-null-img.png"
        } else {
            val resId = application.resources.getIdentifier(imageName, "drawable", application.packageName)
            if (resId != 0) {
                resId.toString()
            } else "https://i.ibb.co/BKpgBf1/na-o-man-null-img.png"
        }
    }
}

