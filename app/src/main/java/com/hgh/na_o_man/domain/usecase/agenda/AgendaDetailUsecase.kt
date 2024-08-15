package com.hgh.na_o_man.domain.usecase.agenda

import com.hgh.na_o_man.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgendaDetailUsecase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend operator fun invoke(agendaId: Long) = flow {
        emit(repository.getAgendaDetail(agendaId))
    }
}