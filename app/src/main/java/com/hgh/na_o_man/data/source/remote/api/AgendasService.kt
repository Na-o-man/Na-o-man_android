package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.agenda.request.AgendaRequestDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaDeleteDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaDetailInfoDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaInfoListDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaSuccessDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AgendasService {
    @GET ("agendas")
    suspend fun  getAllAgenda(
        @Query("shareGroupId") shareGroupId : Long,
        @Query ("page") page : Int,
        @Query ("size") size : Int
    ) : Response<ApiResult<AgendaInfoListDto>>

    @POST("agendas")
    suspend fun postCreateAgenda(
        @Body request: AgendaRequestDto
    ) : Response<ApiResult<AgendaSuccessDto>>

    @GET("agendas/{agendaId}")
    suspend fun getSearchAgenda(
        @Path ("agendaId") agendaId: Long,
    ) : Response<ApiResult<AgendaDetailInfoDto>>

    @DELETE("agendas/{agendaId}")
    suspend fun deleteSpecificAgenda(
        @Path ("agendaId") agendaId: Long,
    ) : Response<ApiResult<AgendaDeleteDto>>

}