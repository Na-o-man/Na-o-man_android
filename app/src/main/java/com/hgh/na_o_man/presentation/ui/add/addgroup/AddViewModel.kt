package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.onException
import com.hgh.na_o_man.di.util.remote.onFail
import com.hgh.na_o_man.di.util.remote.onSuccess
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.usecase.share_group.CreateGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.*
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
            is AddEvent.UpdateGroupName -> updateGroupName(event.groupName) // 새로운 이벤트 추가
        }
    }

    private fun updateGroupName(newName: String) {
        updateState { copy(groupName = newName) }
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
            // ViewState에서 필요한 데이터를 가져옵니다.
            val groupName = viewState.value.groupName
            val memberNames = viewState.value.memberNames
            val place = viewState.value.place
            val attributes = viewState.value.selectedAttributes

            // 요청 데이터 클래스를 생성합니다.
            val requestDto = GroupAddRequestDto(
                meetingTypeList = attributes,
                memberCount = memberNames.size,
                memberNameList = memberNames,
                place = place
            )

            // Usecase 호출
            createGroupUsecase(requestDto).collect { result ->
                result.onSuccess { groupAddDto ->
                    // 그룹 생성 성공, 상태 업데이트
                    updateState {
                        copy(
                            isGroupCreated = true,
                            groupName = groupAddDto.name, // 그룹 이름 업데이트
                            inviteLink = groupAddDto.inviteUrl // 초대 링크 업데이트
                        )
                    }
                    // 효과를 보내어 다음 화면으로 이동
                    sendEffect({AddSideEffect.NavigateToNextScreen})
                }.onFail { error ->
                    // 실패 시, 에러 메시지 출력
                    sendEffect({AddSideEffect.ShowToast("서버와 연결을 실패했습니다. 오류: ${error}")})
                }.onException { e ->
                    // 예외 발생 시, 예외 로그 출력
                    Log.e("예외받기", "Exception: $e")
                    sendEffect({AddSideEffect.ShowToast("알 수 없는 오류가 발생했습니다.")})
                }
            }
        } catch (e: Exception) {
            // 전체 예외 처리
            Log.e("예외받기", "Exception: $e")
            sendEffect({AddSideEffect.ShowToast("알 수 없는 오류가 발생했습니다.")})
        }
    }

    fun getGroupName(): String = viewState.value.groupName
}
