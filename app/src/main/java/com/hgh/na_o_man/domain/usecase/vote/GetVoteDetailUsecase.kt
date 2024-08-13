package com.hgh.na_o_man.domain.usecase.vote

import com.hgh.na_o_man.domain.repository.VoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVoteDetailUsecase @Inject constructor(
    private val repository: VoteRepository
) {
    suspend operator fun invoke(agendaId : Long) = flow {
        emit(repository.getVoteDetail(agendaId))
    }
}