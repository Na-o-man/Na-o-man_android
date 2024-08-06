package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.data.dto.notification.response.AcknowledgedCountDto
import com.hgh.na_o_man.data.dto.notification.response.DeletedCountDto
import com.hgh.na_o_man.data.dto.notification.response.NotificationInfoListDto
import com.hgh.na_o_man.data.dto.notification.response.UnreadDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationsService {

    @POST("notifications/token")
    suspend fun postNotificationAPI(
        @Body fcmRequestDto: FcmRequestDto
    ): Response<ApiResult<Nothing>>

    @POST("notifications/acknowledgements")
    suspend fun postNotificationAckAPI(
    ) : Response<ApiResult<AcknowledgedCountDto>>

    @GET("notifications/unread")
    suspend fun getNotificationUnreadAPI(): Response<ApiResult<UnreadDto>>

    @GET("notifications/my")
    suspend fun getNotificationMyAPI(
        @Query("page") page : Int,
        @Query("size") size : Int,
    ) : Response<ApiResult<NotificationInfoListDto>>

    @DELETE("notifications")
    suspend fun deleteNotifiationCountAPI() : Response<ApiResult<DeletedCountDto>>

    @DELETE("notifications/{notificationId}")
    suspend fun deleteAcknowledgedCountAPI(
        @Path("notificationId") notificationId : Long
    ) : Response<ApiResult<AcknowledgedCountDto>>
}