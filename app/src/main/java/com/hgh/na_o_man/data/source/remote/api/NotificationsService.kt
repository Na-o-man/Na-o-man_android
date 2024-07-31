package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.data.dto.notification.response.UnreadDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotificationsService {
    @POST("notifications/token")
    suspend fun postNotificationAPI(
        @Body fcmRequestDto: FcmRequestDto
    ): Response<ApiResult<Nothing>>

    //    @POST("notifications/acknowledgements")
//    suspend fun postNotificationAck(
//    ) : Response<ApiResult<>>
    @GET("notifications/unread")
    suspend fun getNotificationUnreadAPI(): Response<ApiResult<UnreadDto>>

}