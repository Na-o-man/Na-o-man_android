package com.hgh.na_o_man.data.dto.notification.request

data class PageNotificationDto(
    val page: Int,
    val size: Int,
    val sort: List<String>
)