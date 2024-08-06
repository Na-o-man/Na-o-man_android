package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.usecase.share_group.CreateGroupUsecase
import com.hgh.na_o_man.domain.usecase.share_group.SearchGroupUsecase
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddEvent
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect.NavigateToNextScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddSideEffect.ShowToast
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract.AddViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    val createGroupUsecase: CreateGroupUsecase,
    val searchGroupUsecase: SearchGroupUsecase,
) : BaseViewModel<AddViewState, AddSideEffect, AddEvent>(
    AddViewState() // 초기 상태 설정
) {

    init {
        Log.d("리컴포저블", "AddViewModel")
    }

    override fun handleEvents(event: AddEvent) {
        when (event) {
            is AddEvent.NextButtonClicked -> {
                // 다음 버튼 클릭 처리 로직
                sendEffect({ NavigateToNextScreen })
            }

            is AddEvent.TextInput -> {
            }

            is AddEvent.LoadData -> {
                // 데이터 로드 처리 로직
                loadPhotos(event.shareGroupId)  // shareGroupId 전달
            }

            is AddEvent.ClearTextInput -> {
                // 텍스트 입력 초기화 처리
            }

            is AddEvent.OnCloudButtonClicked -> {
                // 구름 버튼 클릭 처리 로직
                // 사진 로드 또는 다른 작업 수행
                loadPhotos(event.shareGroupId)
            }

            is AddEvent.CreateGroup -> {
                createGroup(event.groupAddRequestDto)
            }
        }
    }

    fun onNextButtonClick(selectedButtons: List<Boolean> = emptyList()) {
        viewModelScope.launch {
            val selectedButtonCount = selectedButtons.count { it }
            if (selectedButtonCount > 0) {
                // 다음 버튼 클릭 이벤트 발생
                setEvent(AddEvent.NextButtonClicked(selectedButtonCount))
            } else {
                // 선택된 버튼이 없을 경우 사용자에게 알림
                sendEffect({ ShowToast("최소 하나의 옵션을 선택해주세요") })
            }
        }
    }

    fun onTextInput(text: String) {
        // 입력 값에 따라 버튼 클릭 이벤트 처리
        if (text.isNotBlank()) {
            setEvent(AddEvent.TextInput(text))
        } else {
            showToast("입력값이 비어있습니다.")
        }
    }

    private fun loadPhotos(shareGroupId: Long) {
        // 사진 로드 로직 구현
        setLoadingState(true) // 로딩 상태 업데이트

        viewModelScope.launch {
            try {
                searchGroupUsecase(shareGroupId).collect { result ->
                    when (result) {
                        is RetrofitResult.Success -> {
                            handleSuccess(result.data)
                        }
                        is RetrofitResult.Error -> {
                            handleError(result.exception)
                        }
                        is RetrofitResult.Fail -> {
                            // Fail 처리 로직 추가
                            setLoadingState(false)
                            sendEffect ({ ShowToast("작업 실패: ${result.message}") })
                        }
                        else -> {
                            // 모든 경우를 처리하지 않았을 때
                            setLoadingState(false)
                            sendEffect ({ ShowToast("알 수 없는 오류 발생") })
                        }
                    }
                }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleSuccess(data: GroupAddModel) {
        // 성공적으로 데이터를 가져온 경우
        val loadedPhotos = data.image // GroupAddModel에서 필요한 사진 리스트를 가져옵니다.
        updateState {
            copy(photos = loadedPhotos.map { it.code }, isLoading = false)
        }
    }

    private fun handleError(exception: Exception) {
        setLoadingState(false)
        sendEffect ({ ShowToast(exception.message ?: "알 수 없는 오류") })
    }

    private fun setLoadingState(isLoading: Boolean) {
        updateState { copy(isLoading = isLoading) }
    }

    // 사용자에게 알림을 보여주는 메소드
    fun showToast(message: String) {
        sendEffect ({ ShowToast(message) })
    }

    private fun createGroup(groupAddRequestDto: GroupAddRequestDto) {
        viewModelScope.launch {
            createGroupUsecase(groupAddRequestDto).collect { result ->
                when (result) {
                    is RetrofitResult.Success -> {
                        // 그룹 생성 성공 처리
                        sendEffect ({ ShowToast("그룹이 성공적으로 생성되었습니다.") })
                    }
                    is RetrofitResult.Error -> {
                        // 오류 처리
                        sendEffect ({ ShowToast("그룹 생성 오류: ${result.exception.message}") })
                    }
                    is RetrofitResult.Fail -> {
                        // 실패 처리
                        sendEffect ({ ShowToast("그룹 생성 실패: ${result.message}") })
                    }
                }
            }
        }
    }
}
