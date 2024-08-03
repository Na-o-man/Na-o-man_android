package com.hgh.na_o_man.data.dto.notification.response

import com.hgh.na_o_man.domain.model.notification.AcknowledgedCountModel

data class AcknowledgedCountDto(
    val acknowledgedCount: Int
) {
    fun toModel() = AcknowledgedCountModel(
        acknowledgedCount = this.acknowledgedCount
    )
}