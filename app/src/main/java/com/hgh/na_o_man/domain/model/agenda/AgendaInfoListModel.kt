package com.hgh.na_o_man.domain.model.agenda

data class AgendaInfoListModel(
    val agendaDetailInfoList: List<AgendaDetailInfoModel>,
    val first: Boolean,
    val last: Boolean,
    val totalElements: Int,
    val totalPages: Int
)
