package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.auth.CheckRegistrationModel
import com.hgh.na_o_man.domain.model.auth.UserTokenModel

interface AuthRepository {
    suspend fun postLogin(loginRequestDto: LoginRequestDto): RetrofitResult<UserTokenModel>
    suspend fun postSignup(signUpRequestDto: SignUpRequestDto): RetrofitResult<UserTokenModel>
    suspend fun getCheckRegistration(loginRequestDto: LoginRequestDto): RetrofitResult<CheckRegistrationModel>
}