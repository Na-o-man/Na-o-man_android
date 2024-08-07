package com.hgh.na_o_man.presentation.ui.add.joingroup

import com.hgh.na_o_man.presentation.base.ViewEvent
import com.hgh.na_o_man.presentation.base.ViewSideEffect
import com.hgh.na_o_man.presentation.base.ViewState

class JoinContract {

    data class JoinViewState(
        val url: String = "", // 입력된 URL
        val isUrlValid: Boolean = false, // URL 유효성 상태
        val showDialog: Boolean = false, // 다이얼로그 표시 여부

    ) : ViewState

    sealed class JoinSideEffect : ViewSideEffect {
        data class _ShowToast(val message: String) : JoinSideEffect() // 토스트 메시지 표시
    }

    sealed class JoinEvent : ViewEvent {
        data class UpdateUrl(val newUrl: String) : JoinEvent() // URL 업데이트 이벤트
        object ValidateUrl : JoinEvent() // URL 검증 이벤트
        object ShowConfirmationDialog : JoinEvent() // 다이얼로그 표시 이벤트
        data class onCorrect(val groupId: Long) : JoinEvent() // groupId 추가
        data class onFind(val inviteCode: String) : JoinEvent() // 초대 코드 검증 이벤트 추가
    }
}
