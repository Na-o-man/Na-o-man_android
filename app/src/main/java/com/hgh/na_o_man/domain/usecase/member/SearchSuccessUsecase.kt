package com.hgh.na_o_man.domain.usecase.member

import com.hgh.na_o_man.domain.repository.MemberRepository
import com.hgh.na_o_man.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchSuccessUsecase @Inject constructor(
    private val repository: MemberRepository
){
    suspend operator fun invoke(memberId : Long) = flow {
        emit(repository.getMemberId(memberId))
    }
}