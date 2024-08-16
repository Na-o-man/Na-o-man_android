package com.hgh.na_o_man.domain.model.agenda

data class AgendaRequestModel (
    val shareGroupId: Long,
    val title: String,
    val photoIdList: List<Long>
)