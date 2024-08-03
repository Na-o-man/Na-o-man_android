package com.hgh.na_o_man.data.dto.notification.response

import com.hgh.na_o_man.domain.model.notification.NotificationInfoModel

data class NotificationInfoDto(
    val body: String,
    val createdAt: String,
    val isChecked: Boolean,
    val url: String
) {
    fun toModel() = NotificationInfoModel(
        body = this.body,
        createdAt = this.createdAt,
        isChecked = this.isChecked,
        url = this.url
    )
}