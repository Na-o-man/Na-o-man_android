package com.hgh.na_o_man.domain.usecase.auth

import com.hgh.na_o_man.data.dto.auth.request.LoginRequestDto
import com.hgh.na_o_man.data.dto.auth.request.SignUpRequestDto
import com.hgh.na_o_man.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignupAuthUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signUpRequestDto: SignUpRequestDto) = flow {
        emit(repository.postSignup(signUpRequestDto))
    }
}