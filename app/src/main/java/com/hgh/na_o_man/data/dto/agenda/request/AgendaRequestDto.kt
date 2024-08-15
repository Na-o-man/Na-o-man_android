package com.hgh.na_o_man.data.dto.agenda.request

import com.hgh.na_o_man.domain.model.agenda.AgendaDeleteModel
import com.hgh.na_o_man.domain.model.agenda.AgendaRequestModel

data class AgendaRequestDto (
    val shareGroupId: Long,
    val title: String,
    val photoIdList: List<Int>
) {
    fun toModel() = AgendaRequestModel(
        shareGroupId = this.shareGroupId,
        title = this.title,
        photoIdList = this.photoIdList
    )
}