package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel
import com.hgh.na_o_man.domain.model.agenda.AgendaSuccessModel

class AgendaSuccessDto (
    val field: String,
    val message: String
){
    fun toModel() = AgendaSuccessModel(
        field = this.field,
        message = this.message
    )
}
