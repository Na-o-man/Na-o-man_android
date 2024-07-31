package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.auth.response.UserTokenDto
import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.data.dto.notification.response.UnreadDto
import com.hgh.na_o_man.data.source.remote.api.NotificationsService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.notification.UnreadModel
import com.hgh.na_o_man.domain.repository.NotificationsRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val api : NotificationsService
) : NotificationsRepository {
    override suspend fun postFcm(fcmRequestDto: FcmRequestDto): RetrofitResult<Nothing> {
        return apiHandler({ api.postNotificationAPI(fcmRequestDto) }) { response: ApiResult<Nothing> -> response.data }
    }

    override suspend fun getUnread(): RetrofitResult<UnreadModel> {
        return apiHandler({ api.getNotificationUnreadAPI() }) { response: ApiResult<UnreadDto> -> response.data.toModel() }
    }
}