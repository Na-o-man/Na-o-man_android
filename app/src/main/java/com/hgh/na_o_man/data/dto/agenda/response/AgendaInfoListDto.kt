package com.hgh.na_o_man.data.dto.agenda.response

import com.hgh.na_o_man.domain.model.agenda.AgendaInfoListModel

data class AgendaInfoListDto(
    val agendaDetailInfoList: List<AgendaDetailInfoDto>,
    val first: Boolean,
    val last: Boolean,
    val totalElements: Int,
    val totalPages: Int
) {
    fun toModel() = AgendaInfoListModel(
        agendaDetailInfoList = this.agendaDetailInfoList.map { it.toModel() },
        first = this.first,
        last = this.last,
        totalElements = this.totalElements,
        totalPages = this.totalPages
    )
}
