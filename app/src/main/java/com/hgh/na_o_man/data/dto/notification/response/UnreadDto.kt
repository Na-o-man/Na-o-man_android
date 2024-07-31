package com.hgh.na_o_man.data.dto.notification.response

import com.hgh.na_o_man.domain.model.notification.UnreadModel

data class UnreadDto(
    val isUnread: Boolean
) {
    fun toModel() = UnreadModel(
        isUnread = this.isUnread
    )
}