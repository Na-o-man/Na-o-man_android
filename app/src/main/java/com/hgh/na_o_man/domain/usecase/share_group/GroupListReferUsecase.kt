package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.data.dto.share_group.response.GroupListReferDto
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GroupListReferUsecase @Inject constructor(
    private val repository: ShareGroupRepository

) {
    suspend fun invoke(page : Int, size : Int) = flow {
        emit(repository.referGroup(page, size))
    }
}