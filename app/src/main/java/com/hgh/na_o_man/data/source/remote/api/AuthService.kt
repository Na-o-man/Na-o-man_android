package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.data.dto.auth.response.CheckRegistrationDto
import com.hgh.na_o_man.data.dto.auth.response.UserTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("auth/signup/android")
    suspend fun postSignUpAPI(
        @Body signupRequest: SignUpRequestDto
    ): Response<ApiResult<UserTokenDto>>

    @POST("auth/login/android")
    suspend fun postLoginAPI(
        @Body loginRequest: LoginRequestDto
    ): Response<ApiResult<UserTokenDto>>

    @POST("auth/check-registration")
    suspend fun getCheckRegistrationAPI(
        @Body loginRequest: LoginRequestDto
    ): Response<ApiResult<CheckRegistrationDto>>

}