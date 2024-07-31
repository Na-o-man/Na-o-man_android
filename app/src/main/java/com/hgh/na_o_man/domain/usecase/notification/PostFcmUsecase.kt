package com.hgh.na_o_man.domain.usecase.notification

import com.hgh.na_o_man.data.dto.notification.request.FcmRequestDto
import com.hgh.na_o_man.domain.repository.AuthRepository
import com.hgh.na_o_man.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostFcmUsecase @Inject constructor(
    private val repository: NotificationsRepository
) {
    suspend operator fun invoke(FcmRequestdto: FcmRequestDto) = flow {
        emit(repository.postFcm(FcmRequestdto))
    }
}