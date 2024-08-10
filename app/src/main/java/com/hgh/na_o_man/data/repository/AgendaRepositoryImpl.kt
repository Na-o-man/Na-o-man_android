package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.agenda.response.AgendaInfoListDto
import com.hgh.na_o_man.data.source.remote.api.AgendasService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel
import com.hgh.na_o_man.domain.repository.AgendaRepository
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val api : AgendasService
) : AgendaRepository{
    override suspend fun getAgenda(shareGroupId: Long, page: Int, size: Int): RetrofitResult<AgendaInfoListModel> {
        return apiHandler({ api.getAllAgenda(shareGroupId,page, size)}) {response : ApiResult<AgendaInfoListDto> -> response.data.toModel()}
    }
}