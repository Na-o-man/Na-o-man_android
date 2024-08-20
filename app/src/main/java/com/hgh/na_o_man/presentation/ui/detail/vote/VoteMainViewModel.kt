package com.hgh.na_o_man.presentation.ui.detail.vote

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.domain.usecase.agenda.AgendaInfoListUsecase
import com.hgh.na_o_man.domain.usecase.share_group.CheckSpecificGroupUsecase
import com.hgh.na_o_man.domain.usecase.share_group.ShareGroupNameListUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.detail.KEY_GROUP_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteMainViewModel @Inject constructor(
    private val agendaInfoListUsecase: AgendaInfoListUsecase,
    private val savedStateHandle: SavedStateHandle,
    private val checkSpecificGroupUsecase: CheckSpecificGroupUsecase,
    private val shareGroupNameListUsecase: ShareGroupNameListUsecase
) : BaseViewModel<VoteMainContract.VoteMainViewState, VoteMainContract.VoteMainSideEffect, VoteMainContract.VoteMainEvent>(
    VoteMainContract.VoteMainViewState()
) {

    private val nextPage = MutableStateFlow(0)
    private val hasNextPage = MutableStateFlow(true)

    init {
        updateState { copy(groupId = savedStateHandle[KEY_GROUP_ID] ?: 0L) }
        fetchGroupName()
        fetchGroupNameList()
        Log.d("리컴포저블", "VoteMainViewModelInit")
    }

//    private val groupId: Long
//        get() = savedStateHandle[KEY_GROUP_ID] ?: 0L


    override fun handleEvents(event: VoteMainContract.VoteMainEvent) {
        when (event) {
            is VoteMainContract.VoteMainEvent.InitVoteMainScreen -> {
                showRefreshVoteList()
            }

            is VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked -> {
                sendEffect({ VoteMainContract.VoteMainSideEffect.NaviAgendaAdd })
            }

            is VoteMainContract.VoteMainEvent.OnClickDropBoxItem -> {
                nextPage.value = 0
                hasNextPage.value = true
                updateState {
                    copy(
                        groupId = event.member.shareGroupId,
                        groupName = event.member.name,
                        voteList = listOf()
                    )
                }
                setEvent(VoteMainContract.VoteMainEvent.OnPagingVoteList)
            }

            VoteMainContract.VoteMainEvent.OnBackClicked -> {
                sendEffect({ VoteMainContract.VoteMainSideEffect.NaviBack })
            }

            is VoteMainContract.VoteMainEvent.OnAgendaItemClicked -> {
                sendEffect({ VoteMainContract.VoteMainSideEffect.NaviVoteDetail(event.agendaId) })
            }

            VoteMainContract.VoteMainEvent.OnPagingVoteList -> {
                showVoteList()
            }
        }
    }

    private fun showVoteList() = viewModelScope.launch {
        Log.d(
            "VoteMainViewModel",
            "Showing vote list, hasNextPage: ${hasNextPage.value}, nextPage: ${nextPage.value}"
        )
        try {
            if (hasNextPage.value) {
                Log.d(
                    "VoteMainViewModel",
                    "Fetching page: ${nextPage.value} for group: ${viewState.value.groupId}"
                )
                agendaInfoListUsecase(
                    viewState.value.groupId,
                    nextPage.value,
                    3
                ).collect { result ->
                    result.onSuccess { response ->
                        Log.d("VoteMainViewModel", "Last page: ${response.last}")
                        updateState {
                            copy(
                                voteList = viewState.value.voteList + response.agendaDetailInfoList,
                                loadState = LoadState.SUCCESS
                            )
                        }
                        response.last.not().let {
                            hasNextPage.value = it
                            nextPage.value += 1
                        }
//                        if(response.last){
//                            hasNextPage.value = false
//                        } else {
//                            nextPage.value += 1
//                        }
                    }.onFail { error ->
                        Log.e("VoteMainViewModel", "Failed to fetch vote list: $error")
                        updateState {
                            copy(loadState = LoadState.ERROR)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("VoteMainViewModel", "Error in showVoteList", e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private fun showRefreshVoteList() = viewModelScope.launch {
        try {
            updateState { copy(voteList = listOf()) }
            agendaInfoListUsecase(viewState.value.groupId, 0, 3).collect { result ->
                result.onSuccess { response ->
                    Log.d("VoteMainViewModel", "Last page: ${response.last}")
                    updateState {
                        copy(
                            voteList = viewState.value.voteList + response.agendaDetailInfoList,
                            loadState = LoadState.SUCCESS
                        )
                    }
                    response.last.not().let {
                        hasNextPage.value = it
                        nextPage.value = 1
                    }
                }.onFail { error ->
                    Log.e("VoteMainViewModel", "Failed to fetch vote list: $error")
                    updateState {
                        copy(loadState = LoadState.ERROR)
                    }

                }
            }
        } catch (e: Exception) {
            Log.e("VoteMainViewModel", "Error in showVoteList", e)
            updateState { copy(loadState = LoadState.ERROR) }
        }
    }

    private fun fetchGroupName() = viewModelScope.launch {
        try {
            checkSpecificGroupUsecase(viewState.value.groupId).collect { result ->
                result.onSuccess { data ->
                    val groupName = data.name
                    Log.d("VoteMainViewModel1", "Fetched group name: $groupName")
                    updateState { copy(groupName = groupName) }
                }.onFail {
                    Log.e("VoteMainViewModel1", "Failed to fetch group name")
                }
            }
        } catch (e: Exception) {
            Log.e("VoteMainViewModel1", "Error in fetchGroupName", e)
        }
    }

    private fun fetchGroupNameList() = viewModelScope.launch {
        try {
            shareGroupNameListUsecase(0, 10).collect { result ->
                result.onSuccess { data ->
                    val groupNameList = data.shareGroupNameInfoList
                    updateState { copy(groupNameList = groupNameList) }
                }.onFail {
                    Log.e("VoteMainViewModel", "Failed to fetch group name list")
                }
            }
        } catch (e: Exception) {
            Log.e("VoteMainViewModel", "Failed to fetch group name list", e)
        }
    }
}