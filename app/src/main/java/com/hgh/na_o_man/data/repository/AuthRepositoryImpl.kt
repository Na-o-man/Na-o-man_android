package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.data.dto.auth.response.CheckRegistrationDto
import com.hgh.na_o_man.data.dto.auth.response.UserTokenDto
import com.hgh.na_o_man.data.source.remote.api.AuthService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.auth.CheckRegistrationModel
import com.hgh.na_o_man.domain.model.auth.UserTokenModel
import com.hgh.na_o_man.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService
) : AuthRepository {
    override suspend fun postLogin(loginRequestDto: LoginRequestDto): RetrofitResult<UserTokenModel> {
        return apiHandler({ api.postLoginAPI(loginRequestDto) }) { response: ApiResult<UserTokenDto> -> response.data.toModel() }
    }

    override suspend fun postSignup(signUpRequestDto: SignUpRequestDto): RetrofitResult<UserTokenModel> {
        return apiHandler({ api.postSignUpAPI(signUpRequestDto) }) { response: ApiResult<UserTokenDto> -> response.data.toModel() }
    }

    override suspend fun getCheckRegistration(email: String): RetrofitResult<CheckRegistrationModel> {
        return apiHandler({ api.getCheckRegistrationAPI(email) }) { response: ApiResult<CheckRegistrationDto> -> response.data.toModel() }
    }

}