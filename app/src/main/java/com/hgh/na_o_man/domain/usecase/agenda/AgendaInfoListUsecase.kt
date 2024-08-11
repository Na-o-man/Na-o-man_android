package com.hgh.na_o_man.domain.usecase.agenda

import com.hgh.na_o_man.data.dto.agenda.response.AgendaInfoListDto
import com.hgh.na_o_man.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgendaInfoListUsecase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend operator fun invoke(shareGroupId : Long, page : Int, size : Int) = flow {
        emit(repository.getAgenda(shareGroupId,page,size))
    }
}