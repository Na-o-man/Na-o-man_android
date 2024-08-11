package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaPhotoInfoModel

data class AgendaPhotoInfoDto(
    val agendaPhotoId: Int,
    val url: String
) {
    fun toModel() = AgendaPhotoInfoModel(
        agendaPhotoId = this.agendaPhotoId,
        url = this.url
    )
}