package com.hgh.na_o_man.data.dto.notification.response

import com.hgh.na_o_man.domain.model.notification.DeletedCountModel

data class DeletedCountDto(
    val deletedCount: Int
) {
    fun toModel() = DeletedCountModel(
        deletedCount = this.deletedCount
    )
}