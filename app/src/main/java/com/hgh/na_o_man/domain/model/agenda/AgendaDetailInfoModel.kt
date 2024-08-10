package com.hgh.na_o_man.domain.model.agenda

data class AgendaDetailInfoModel(
    val agendaId: Int,
    val agendaPhotoInfoList: List<AgendaPhotoInfoModel>,
    val title: String
)
