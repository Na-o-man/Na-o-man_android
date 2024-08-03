package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.data.dto.notification.request.PageNotificationDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.notification.AcknowledgedCountModel
import com.hgh.na_o_man.domain.model.notification.DeletedCountModel
import com.hgh.na_o_man.domain.model.notification.NotificationInfoListModel
import com.hgh.na_o_man.domain.model.notification.UnreadModel
import retrofit2.Retrofit

interface NotificationsRepository {
    suspend fun postFcm(fcmRequestDto: FcmRequestDto) : RetrofitResult<Nothing>
    suspend fun postacknowledgeCount() : RetrofitResult<AcknowledgedCountModel>
    suspend fun getUnread() : RetrofitResult<UnreadModel>
    suspend fun getNotificationInfoList(pageNotificationDto: PageNotificationDto) : RetrofitResult<NotificationInfoListModel>
    suspend fun deleteDeletedCount() : RetrofitResult<DeletedCountModel>
    suspend fun deleteAcknowledgedCount(notificationId : Long) : RetrofitResult<AcknowledgedCountModel>
}