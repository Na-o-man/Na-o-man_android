package com.hgh.na_o_man.domain.usecase.notification

import com.hgh.na_o_man.data.dto.notification.request.PageNotificationDto
import com.hgh.na_o_man.data.dto.notification.response.NotificationInfoListDto
import com.hgh.na_o_man.domain.model.notification.NotificationInfoListModel
import com.hgh.na_o_man.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationInfoListUsecase @Inject constructor(
    private val repository: NotificationsRepository
){
    suspend operator fun invoke(pageNotificationDto: PageNotificationDto) = flow {
        emit(repository.getNotificationInfoList(pageNotificationDto))
    }
}

