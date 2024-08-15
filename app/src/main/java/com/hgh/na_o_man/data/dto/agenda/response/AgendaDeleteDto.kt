package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel

data class AgendaDeleteDto (
    val agendaId: Long,
    val createdAt: String
){
    fun toModel() = AgendaDeleteModel(
        agendaId = this.agendaId,
        createdAt = this.createdAt
    )
}

