package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.agenda.request.AgendaRequestDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaDeleteDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaDetailInfoDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaInfoListDto
import com.hgh.na_o_man.data.dto.agenda.response.AgendaSuccessDto
import com.hgh.na_o_man.data.source.remote.api.AgendasService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel
import com.hgh.na_o_man.domain.model.agenda.AgendaDetailInfoModel
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel
import com.hgh.na_o_man.domain.model.agenda.AgendaSuccessModel
import com.hgh.na_o_man.domain.repository.AgendaRepository
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val api : AgendasService
) : AgendaRepository{
    override suspend fun getAgenda(shareGroupId: Long, page: Int, size: Int): RetrofitResult<AgendaInfoListModel> {
        return apiHandler({ api.getAllAgenda(shareGroupId,page, size)}) {response : ApiResult<AgendaInfoListDto> -> response.data.toModel()}
    }
    override suspend fun postAgenda(agendaRequestDto: AgendaRequestDto): RetrofitResult<AgendaSuccessModel> {
        return apiHandler({ api.postCreateAgenda(agendaRequestDto) }) {response : ApiResult<AgendaSuccessDto> -> response.data.toModel()}
    }
    override suspend fun getAgendaDetail(agendaId: Long): RetrofitResult<AgendaDetailInfoModel> {
        return apiHandler({ api.getSearchAgenda(agendaId) }) {response : ApiResult<AgendaDetailInfoDto> -> response.data.toModel()}
    }
    override suspend fun deleteAgenda(agendaId: Long): RetrofitResult<AgendaDeleteModel> {
        return apiHandler({ api.deleteSpecificAgenda(agendaId) }) {response : ApiResult<AgendaDeleteDto> -> response.data.toModel()}
    }
}