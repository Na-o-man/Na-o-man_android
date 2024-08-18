package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.share_group.CreateGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddEvent
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AddViewModel @Inject constructor(
    private val createGroupUsecase: CreateGroupUsecase
) : BaseViewModel<AddViewState, AddSideEffect, AddEvent>(
    AddViewState()
) {

    init {
        Log.d("리컴포저블", "AddViewModel")
    }

    override fun handleEvents(event: AddEvent) {
        when (event) {
            is AddEvent.AddMember -> addMember(event.name)
            is AddEvent.CreateGroup -> createGroup()
            is AddEvent.RemoveMember -> removeMember(event.name)
            is AddEvent.UpdateSelectedAttributes -> updateSelectedAttributes(event.attributes)
            is AddEvent.Back -> sendEffect ({ AddSideEffect.Back })
        }
    }

    private fun addMember(name: String) {
        val currentMembers = viewState.value.memberNames.toMutableList()
        if (name.isNotBlank() && !currentMembers.contains(name)) {
            currentMembers.add(name)
            updateState { copy(memberNames = currentMembers, memberCount = currentMembers.size) }
        }
    }

    private fun removeMember(name: String) {
        val currentMembers = viewState.value.memberNames.toMutableList()
        if (currentMembers.contains(name)) {
            currentMembers.remove(name)
            updateState { copy(memberNames = currentMembers, memberCount = currentMembers.size) }
        }
    }

    private fun updateSelectedAttributes(attributes: List<String>) {
        // 버튼 9개
        Log.d("UpdateAttributes", "Attributes: $attributes")
        if (viewState.value.selectedAttributes != attributes) {
            updateState { copy(selectedAttributes = attributes) }
        }
    }

    fun updatePlace(newPlace: String) {
        if (viewState.value.place != newPlace) {
            updateState { copy(place = newPlace) }
        }
    }

    private fun createGroup() = viewModelScope.launch {
        try {
            val memberNames = viewState.value.memberNames
            val place = viewState.value.place
            val attributes = viewState.value.selectedAttributes

            if (memberNames.isEmpty() || place.isBlank() || attributes.isEmpty()) {
                Log.e("CreateGroup", "필수 필드가 누락되었습니다.")
                sendEffect ({ AddSideEffect.ShowToast("필수 필드가 누락되었습니다.") })
                return@launch
            }

            val requestDto = GroupAddRequestDto(
                meetingTypeList = attributes,
                memberCount = memberNames.size,
                memberNameList = memberNames,
                place = place
            )

            Log.d("CreateGroupRequest", "Request DTO: $requestDto")

            createGroupUsecase(requestDto).collect { result ->
                result.onSuccess { response ->
                    updateState {
                        copy(
                            isGroupCreated = true,
                            groupName = response.name,
                            inviteLink = response.inviteUrl
                        )
                    }
                    sendEffect({AddSideEffect.NavigateToNextScreen})
                }.onFail { error ->
                    sendEffect({AddSideEffect.ShowToast("서버와 연결을 실패했습니다. 오류: $error")})
                }.onException { e ->
                    Log.e("예외받기", "Exception: $e")
                    sendEffect({AddSideEffect.ShowToast("알 수 없는 오류가 발생했습니다.")})
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "Exception: $e")
            sendEffect({AddSideEffect.ShowToast("알 수 없는 오류가 발생했습니다.")})
        }
    }
}

