package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaDetailInfoModel

data class AgendaDetailInfoDto(
    val agendaId: Long,
    val agendaPhotoInfoList: List<AgendaPhotoInfoDto>,
    val title: String
) {
    fun toModel() = AgendaDetailInfoModel(
        agendaId = this.agendaId,
        agendaPhotoInfoList = this.agendaPhotoInfoList.map { it.toModel() },
        title = this.title
    )
}