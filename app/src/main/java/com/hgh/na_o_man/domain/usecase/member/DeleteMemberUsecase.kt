package com.hgh.na_o_man.domain.usecase.member

import com.hgh.na_o_man.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteMemberUsecase @Inject constructor(
    private val repository: MemberRepository
) {
    suspend operator fun invoke() = flow {
        emit(repository.deleteMember())
    }
}