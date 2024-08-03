package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JoinGroupUsecase @Inject constructor(
    private val repository: ShareGroupRepository
) {
    suspend operator fun invoke(groupJoinRequestDto: GroupJoinRequestDto) = flow {
        emit(repository.postJoin(groupJoinRequestDto))
    }
}