package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.vote.request.VoteRequestDto
import com.hgh.na_o_man.data.dto.vote.response.VoteDetailDto
import com.hgh.na_o_man.data.dto.vote.response.VoteIdsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VoteService {
    @GET("agendas/{agendaId}/vote")
    suspend fun getVoteDetailAPI(
        @Path("agendaId") agendaId : Long
    ): Response<ApiResult<List<VoteDetailDto>>>

    @POST("agendas/{agendaId}/vote")
    suspend fun postVoteAPI(
        @Path("agendaId") agendaId : Long,
        @Body request : VoteRequestDto
    ): Response<ApiResult<VoteIdsDto>>
}