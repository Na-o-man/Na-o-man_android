package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.vote.request.VoteRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel
import com.hgh.na_o_man.domain.model.vote.VoteAgendaDetailModel
import com.hgh.na_o_man.domain.model.vote.VoteIdsModel

interface VoteRepository {
    suspend fun getVoteDetail(agendaId : Long) : RetrofitResult<VoteAgendaDetailModel>
    suspend fun postVote(agendaId : Long, voteRequestDto: VoteRequestDto) : RetrofitResult<VoteIdsModel>
}