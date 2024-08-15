package com.hgh.na_o_man.presentation.ui.detail.vote_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.vote.request.VoteCommentDto
import com.hgh.na_o_man.data.dto.vote.request.VoteRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.domain.usecase.agenda.AgendaDetailUsecase
import com.hgh.na_o_man.domain.usecase.member.GetMyInfoUsecase
import com.hgh.na_o_man.domain.usecase.vote.GetVoteDetailUsecase
import com.hgh.na_o_man.domain.usecase.vote.PostVoteUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.detail.KEY_AGENDA_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMyInfoUsecase: GetMyInfoUsecase,
    private val getVoteDetailUsecase: GetVoteDetailUsecase,
    private val getAgendasUsecase: AgendaDetailUsecase,
    private val postVoteUsecase: PostVoteUsecase,
) : BaseViewModel<VoteDetailContract.VoteDetailViewState, VoteDetailContract.VoteDetailSideEffect, VoteDetailContract.VoteDetailEvent>(
    VoteDetailContract.VoteDetailViewState()
) {
    init {
        updateState {
            copy(
                agendaId = savedStateHandle[KEY_AGENDA_ID] ?: -1L,
            )
        }
        getVoteDetail()
        Log.d("리컴포저블", "VoteDetailViewModel")
    }

    override fun handleEvents(event: VoteDetailContract.VoteDetailEvent) {
        when (event) {
            VoteDetailContract.VoteDetailEvent.InitVoteDetailScreen -> {

            }

            VoteDetailContract.VoteDetailEvent.OnClickFinish -> {
                postVote()
            }

            VoteDetailContract.VoteDetailEvent.OnCLickBack -> {
                sendEffect({ VoteDetailContract.VoteDetailSideEffect.NaviBack })
            }

            VoteDetailContract.VoteDetailEvent.OnClickVote -> {
                updateState { copy(isVoteMode = true) }
                getMyInfo()
            }

            is VoteDetailContract.VoteDetailEvent.OnCLickNotVoteModeImage -> {
                updateState { copy(isDialogVisible = true, clickAgenda = event.vote) }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickVoteModeImage -> {
                updateState { copy(isDialogVisible = true, clickAgenda = event.vote) }
            }

            VoteDetailContract.VoteDetailEvent.OnDialogClosed -> {
                updateState { copy(isDialogVisible = false) }
            }

            VoteDetailContract.VoteDetailEvent.OnClickBackOnVote -> {
                updateState {
                    updateState {
                        copy(
                            agendas = agendas.map {
                                if (it.photoInfo.isVoted) {
                                    it.copy(
                                        photoInfo = it.photoInfo.copy(
                                            isVoted = false,
                                            comment = "",
                                        )
                                    )
                                } else {
                                    it
                                }
                            },
                            isVoteMode = false
                        )
                    }
                    copy(photos = photos.map {
                        if (it.is3) it.copy(is3 = false) else it
                    }, isVoteMode = false)
                }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickInject -> {
                updateState {
                    copy(
                        agendas = agendas.map {
                            if (it.agendaPhotoId == event.agendaPhotoId) {
                                it.copy(
                                    photoInfo = it.photoInfo.copy(
                                        isVoted = true,
                                        comment = event.text
                                    )
                                )
                            } else {
                                it
                            }
                        },
                        isDialogVisible = false
                    )
                }
            }

            is VoteDetailContract.VoteDetailEvent.OnClickCancelVote -> {
                updateState {
                    copy(
                        agendas = agendas.map {
                            if (it.agendaPhotoId == event.agendaPhotoId) {
                                it.copy(
                                    photoInfo = it.photoInfo.copy(
                                        isVoted = false,
                                        comment = ""
                                    )
                                )
                            } else {
                                it
                            }
                        }
                    )
                }
            }
        }
    }

    private fun getMyInfo() = viewModelScope.launch {
        try {
            getMyInfoUsecase().collect { result ->
                result.onSuccess {
                    updateState { copy(userInfo = it) }
                }.onFail {
                    updateState { copy(loadState = LoadState.ERROR) }
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }


    private fun getVoteDetail() = viewModelScope.launch {
        try {
            getAgendasUsecase(viewState.value.agendaId).collect { result ->
                result.onSuccess { agendaData ->
                    updateState {
                        copy(title = agendaData.title)
                    }
                    getVoteDetailUsecase(viewState.value.agendaId).collect { agendaResult ->
                        agendaResult.onSuccess { voteData ->
                            val voteMap =
                                agendaData.agendaPhotoInfoList.associateBy { it.agendaPhotoId }

                            updateState {
                                copy(agendas = voteData.agendaDetails.map { agenda ->
                                    agenda.copy(
                                        photoInfo = PhotoInfoModel(
                                            rawPhotoUrl = voteMap[agenda.agendaPhotoId]?.url
                                                ?: ""
                                        )
                                    )
                                })
                            }
                        }.onFail {
                            updateState { copy(loadState = LoadState.ERROR) }
                        }.onException {
                            throw it
                        }
                    }
                }.onFail {
                    updateState { copy(loadState = LoadState.ERROR) }
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }

    private fun postVote() = viewModelScope.launch {
        try {
            postVoteUsecase(
                agendaId = viewState.value.agendaId,
                voteRequestDto = VoteRequestDto(
                    voteInfoList = viewState.value.agendas.filter { agenda ->
                        agenda.photoInfo.isVoted
                    }.map {
                        VoteCommentDto(
                            agendaPhotoId = it.agendaPhotoId,
                            comment = it.photoInfo.comment
                        )
                    }
                )
            ).collect { result ->
                result.onSuccess {
                    setEvent(VoteDetailContract.VoteDetailEvent.OnClickBackOnVote)
                    getVoteDetail()
                }.onFail { errorCode ->
                    if (errorCode == 400) {
                        sendEffect({ VoteDetailContract.VoteDetailSideEffect.ShowToast("이미 투표한 안건 입니다.") })
                    } else {
                        sendEffect({ VoteDetailContract.VoteDetailSideEffect.ShowToast("서버와 연결을 실패했습니다.") })
                    }
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }
}
