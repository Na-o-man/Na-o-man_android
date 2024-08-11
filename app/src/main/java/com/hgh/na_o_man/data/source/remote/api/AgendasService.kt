package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.agenda.response.AgendaInfoListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AgendasService {
    @GET ("agendas")
    suspend fun  getAllAgenda(
        @Query("shareGroupId") shareGroupId : Long,
        @Query ("page") page : Int,
        @Query ("size") size : Int
    ) : Response<ApiResult<AgendaInfoListDto>>
}