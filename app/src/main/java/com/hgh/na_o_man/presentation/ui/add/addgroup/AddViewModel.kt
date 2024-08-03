package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import com.hgh.na_o_man.presentation.base.BaseViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.ui.add.AddContract.AddEvent
import com.hgh.na_o_man.presentation.ui.add.AddContract.AddSideEffect
import com.hgh.na_o_man.presentation.ui.add.AddContract.AddSideEffect.*
import com.hgh.na_o_man.presentation.ui.add.AddContract.AddViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class AddViewModel @Inject constructor(
) : BaseViewModel<AddViewState, AddSideEffect, AddEvent>(
    AddViewState() // 초기 상태 설정
) {
    private val membersList = mutableListOf<String>() // 멤버 리스트

    init {
        Log.d("리컴포저블", "AddViewModel")
        addSelfToGroup2()
    }

    fun addSelfToGroup2() {
        Log.d("AddViewModel", "addSelfToGroup2() called")
        // 자신의 프로필을 자동으로 추가합니다.
        val selfNickname = "자신의 닉네임" // 실제 닉네임을 가져와야 함
        membersList.add(0, selfNickname) // 항상 최상단에 추가
        updateState { copy(nickname = selfNickname) }
    }

    // 닉네임 추가 메서드
    fun addNicknameToGroup(nickname: String) {
        if (nickname.isNotBlank()) {
            membersList.add(0, nickname) // 새로운 닉네임을 최상단에 추가
            updateState { copy(nickname = nickname) }
        }
    }

    override fun handleEvents(event: AddEvent) {
        when (event) {
            is AddEvent.ChangeNickname -> {
                // 닉네임 변경 로직을 작성합니다.
                updateState { copy(nickname = event.newNickname) }
            }
            is AddEvent.AddGroup1 -> {
                // 인원 수 처리
                handleAddGroup1(event.textValue) // textValue를 이벤트에서 받음
            }
            is AddEvent.AddGroup2 -> {
                // AddGroup2 기능 추가
                addGroup2()
            }
            is AddEvent.AddGroup3 -> {
                handleAddGroup3(event.selectedButtons, event.inputText)
            }
            is AddEvent.AddGroup4 -> {
//                if (event.textValue.isNotBlank()) {
//                    navigate("members_loading") // 다음 화면으로 이동
//                    // 이후 AddGroup5 이벤트를 호출
//                    addGroup5() // AddGroup5 로직 호출
//                } else {
//                    sendEffect ({ ShowErrorMessage("유효한 텍스트를 입력해주세요.") }) // 에러 메시지 전송
//                }
            }
            is AddEvent.AddGroup5 -> {
//                // MembersLoading으로 이동 후 3초 대기 후 MembersFolder로 이동
//                navigate("members_loading") // MembersLoading 화면으로 이동
//                // 5초 후 MembersFolder로 이동
//                viewModelScope.launch {
//                    delay(5000) // 5초 대기
//                    navigate("members_folder") // MembersFolder로 이동
//                }
            }
            is AddEvent.AddGroup6 -> {
//                // 다음 스크린으로 이동하는 로직
//                navigate("accept_check_screen") // 원하는 경로로 변경
            }
            is AddEvent.AddGroup7 -> {
//                // AcceptWhoScreen으로 이동하는 로직
//                navigate("accept_who_screen")
            }
            is AddEvent.AddGroup8 -> {
//                // MembersLoading으로 이동
//                navigate("members_loading")
//                // 5초 후에 돌아오기
//                viewModelScope.launch {
//                    delay(3000) // 5초 대기
//                    navController.popBackStack() // 이전 화면으로 돌아가기
//                }
            }
        }
    }

    private fun addGroup1() {
        // AddGroup2 기능 구현
        Log.d("AddViewModel", "addGroup1() called")
    }

    private fun addGroup2() {
        // AddGroup2 기능 구현
        Log.d("AddViewModel", "addGroup2() called")
    }

    private fun handleAddGroup1(textValue: String) {
        // 인원 수를 처리하는 로직 구현
        Log.d("AddViewModel", "addGroup1() called with textValue: $textValue")

        // 현재 상태에서 memberCount 가져오기
        val currentState = viewState.value.memberCount // 현재 상태를 가져옵니다.

        // 인원 수가 유효한지 체크하고 다음 화면으로 이동
        if (textValue.isNotEmpty() && textValue.all { it.isDigit() }) {
            val count = textValue.toInt()
            updateState { copy(memberCount = count) } // 상태 업데이트 (인원 수 저장)
            sendEffect ({ NavigateToNextScreen }) // 다음 화면으로 이동하는 사이드 이펙트 전송
        } else {
            // 인원 수가 비어 있거나 유효하지 않을 때의 로직
            sendEffect ({ ShowErrorMessage("유효한 인원 수를 입력해주세요.") }) // 에러 메시지 전송
        }
    }

    private fun handleAddGroup3(selectedButtons: List<String>, inputText: String) {
        Log.d("AddViewModel", "handleAddGroup3() called with selectedButtons: $selectedButtons and inputText: $inputText")

        // 중복 선택된 버튼이 있는지 확인
        val uniqueSelectedButtons = selectedButtons.distinct()

        // 동일한 텍스트가 버튼에 포함되어 있는지 확인
        val isTextMatched = uniqueSelectedButtons.any { it == inputText }

        if (uniqueSelectedButtons.size > 1 || isTextMatched) {
            // 조건을 만족하면 다음 화면으로 이동
            sendEffect ({ NavigateToNextScreen })
        } else {
            // 조건을 만족하지 않으면 에러 메시지 전송
            sendEffect ({ ShowErrorMessage("버튼을 중복 선택하거나 동일한 텍스트를 입력해주세요.") })
        }
    }

    fun addGroup5() {
        // AddGroup5 관련 로직을 여기에 추가
        Log.d("AddViewModel", "addGroup5() called")
        // AddGroup5 이벤트를 직접 처리, 상태 업데이트 등을 여기에 추가.
        updateState { copy(loadState = LoadState.LOADING) } // 로딩 상태로 업데이트
        // 이후 AddGroup5 이벤트를 발생시키기 위해 이벤트 핸들러가 호출됩니다.
        handleEvents(AddEvent.AddGroup5) // AddGroup5 이벤트 직접 처리
    }


}

//class YourViewModel(private val navController: NavController) : ViewModel() {
//
//    // 다른 로직들...
//
//    fun navigateUp() {
//        // NavController를 사용하여 뒤로가기
//        navController.navigateUp()
//    }
//
//    fun navigate(route: String) {
//        // NavController를 사용하여 특정 route로 이동
//        navController.navigate(route)
//    }
//}
