package com.hgh.na_o_man.domain.model.notification

data class NotificationInfoListModel(
    val isFirst: Boolean,
    val isLast: Boolean,
    val notificationInfoList: List<NotificationInfoModel>,
    val totalElements: Int,
    val totalPage: Int
)