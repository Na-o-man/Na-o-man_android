package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel
import com.hgh.na_o_man.domain.model.agenda.AgendaSuccessModel

class AgendaSuccessDto (
    val agendaId: Long,
    val createdAt: String
){
    fun toModel() = AgendaSuccessModel(
        agendaId = this.agendaId,
        createdAt = this.createdAt
    )
}
