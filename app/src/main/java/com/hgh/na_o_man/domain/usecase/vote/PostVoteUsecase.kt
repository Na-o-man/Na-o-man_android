package com.hgh.na_o_man.domain.usecase.vote

import com.hgh.na_o_man.data.dto.vote.request.VoteRequestDto
import com.hgh.na_o_man.domain.repository.VoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostVoteUsecase @Inject constructor(
    private val repository: VoteRepository
) {
    suspend operator fun invoke(agendaId: Long, voteRequestDto: VoteRequestDto) = flow {
        emit(repository.postVote(agendaId, voteRequestDto))
    }
}