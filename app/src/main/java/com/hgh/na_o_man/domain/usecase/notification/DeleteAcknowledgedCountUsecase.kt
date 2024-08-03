package com.hgh.na_o_man.domain.usecase.notification

import com.hgh.na_o_man.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteAcknowledgedCountUsecase @Inject constructor(
    private val repository: NotificationsRepository
) {
    suspend operator fun invoke(notificationId : Long) = flow {
        emit(repository.deleteAcknowledgedCount(notificationId))
    }
}