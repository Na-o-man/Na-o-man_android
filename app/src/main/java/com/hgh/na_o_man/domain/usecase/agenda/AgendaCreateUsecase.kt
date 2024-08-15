package com.hgh.na_o_man.domain.usecase.agenda

import android.util.Log
import com.hgh.na_o_man.data.dto.agenda.request.AgendaRequestDto
import com.hgh.na_o_man.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgendaCreateUsecase @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend operator fun invoke(agendaRequestDto: AgendaRequestDto) = flow {
        emit(repository.postAgenda(agendaRequestDto))
    }
}