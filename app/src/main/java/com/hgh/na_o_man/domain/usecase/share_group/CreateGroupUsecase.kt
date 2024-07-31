package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.domain.repository.AuthRepository
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateGroupUsecase @Inject constructor(
    private val repository: ShareGroupRepository
) {
    suspend operator fun invoke(groupAddRequestDto: GroupAddRequestDto) = flow {
        emit(repository.postShare(groupAddRequestDto))
    }
}