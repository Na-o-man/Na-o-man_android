package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.vote.response.VoteDetailDto
import com.hgh.na_o_man.data.source.remote.api.VoteService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.vote.VoteAgendaDetailModel
import com.hgh.na_o_man.domain.repository.VoteRepository
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val api: VoteService
) : VoteRepository {
    override suspend fun getVoteDetail(agendaId: Long): RetrofitResult<VoteAgendaDetailModel> {
        return apiHandler({ api.getVoteDetailAPI(agendaId) }) { response: ApiResult<List<VoteDetailDto>> ->
            VoteAgendaDetailModel(
                agendaDetails = response.data.map { it.toModel() }
            )
        }
    }
}
