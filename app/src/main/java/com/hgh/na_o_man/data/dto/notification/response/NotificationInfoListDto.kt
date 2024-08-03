package com.hgh.na_o_man.data.dto.notification.response

import com.hgh.na_o_man.domain.model.notification.NotificationInfoListModel

data class NotificationInfoListDto(
    val isFirst: Boolean,
    val isLast: Boolean,
    val notificationInfoList: List<NotificationInfoDto>,
    val totalElements: Int,
    val totalPage: Int
) {
    fun toModel() = NotificationInfoListModel(
        isFirst = this.isFirst,
        isLast = this.isLast,
        notificationInfoList = this.notificationInfoList.map{it.toModel()},
        totalElements = this.totalElements,
        totalPage = this.totalPage
    )
}