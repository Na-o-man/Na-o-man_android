package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.notification.UnreadModel
import retrofit2.Retrofit

interface NotificationsRepository {
    suspend fun postFcm(fcmRequestDto: FcmRequestDto) : RetrofitResult<Nothing>
    suspend fun getUnread() : RetrofitResult<UnreadModel>
}