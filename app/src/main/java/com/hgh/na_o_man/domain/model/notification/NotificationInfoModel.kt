package com.hgh.na_o_man.domain.model.notification

data class NotificationInfoModel(
    val body: String,
    val createdAt: String,
    val isChecked: Boolean,
    val url: String
)