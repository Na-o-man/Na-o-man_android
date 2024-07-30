package com.hgh.na_o_man.domain.usecase.auth

import com.hgh.na_o_man.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckRegistrationUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String) = flow {
        emit(repository.getCheckRegistration(email))
    }
}