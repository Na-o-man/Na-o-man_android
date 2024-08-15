package com.hgh.na_o_man.domain.model.agenda

data class AgendaDetailInfoModel(
    val agendaId: Long = -1L,
    val agendaPhotoInfoList: List<AgendaPhotoInfoModel> = listOf(),
    val title: String = ""
)
