package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.usecase.share_group.CreateGroupUsecase
import com.hgh.na_o_man.domain.usecase.share_group.JoinGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val createGroupUsecase: CreateGroupUsecase
) : BaseViewModel<AddContract.AddViewState, AddContract.AddSideEffect, AddContract.AddEvent>(
    AddContract.AddViewState()
) {

    init {
        Log.d("리컴포저블", "AddViewModel")
    }

    override fun handleEvents(event: AddContract.AddEvent) {
        when (event) {
            is AddContract.AddEvent.AddMember -> addMember(event.name)
            is AddContract.AddEvent.CreateGroup -> createGroup()
            is AddContract.AddEvent.RemoveMember -> removeMember(event.name)
            is AddContract.AddEvent.UpdateSelectedAttributes -> updateSelectedAttributes(event.attributes)
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
        updateState { copy(selectedAttributes = attributes) }
    }

    fun updatePlace(newPlace: String) {
        updateState { copy(place = newPlace) }
    }

    private fun createGroup() = viewModelScope.launch {
        try {
            val groupName = viewState.value.groupName
            val memberNames = viewState.value.memberNames
            val place = viewState.value.place
            val attributes = viewState.value.selectedAttributes

            createGroupUsecase(
                GroupAddRequestDto(
                    meetingTypeList = attributes,
                    memberCount = memberNames.size,
                    memberNameList = memberNames,
                    place = place
                )
            ).collect { result ->
                result.onSuccess { groupAddDto ->
                    // 그룹 생성 성공, 상태 업데이트
                    updateState {
                        copy(
                            isGroupCreated = true,
                            groupName = groupAddDto.name, // 그룹 이름 업데이트
                            inviteLink = groupAddDto.inviteUrl // 초대 링크 업데이트
                        )
                    }
                    sendEffect({AddSideEffect.NavigateToNextScreen})
                }.onFail {
                    sendEffect({AddSideEffect.ShowToast("서버와 연결을 실패했습니다.")})
                }.onException {
                    throw it
                }
            }
        } catch (e: Exception) {
            Log.e("예외받기", "$e")
        }
    }
}
