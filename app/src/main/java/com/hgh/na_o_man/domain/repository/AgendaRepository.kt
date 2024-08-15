package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.agenda.request.AgendaRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel
import com.hgh.na_o_man.domain.model.agenda.AgendaDetailInfoModel
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel
import com.hgh.na_o_man.domain.model.agenda.AgendaSuccessModel

interface AgendaRepository {
    suspend fun getAgenda(shareGroupId : Long, page : Int, size : Int) : RetrofitResult<AgendaInfoListModel>
    suspend fun postAgenda(agendaRequestDto: AgendaRequestDto): RetrofitResult<AgendaSuccessModel>
    suspend fun getAgendaDetail(agendaId: Long): RetrofitResult<AgendaDetailInfoModel>
    suspend fun deleteAgenda(agendaId: Long): RetrofitResult<AgendaDeleteModel>
}