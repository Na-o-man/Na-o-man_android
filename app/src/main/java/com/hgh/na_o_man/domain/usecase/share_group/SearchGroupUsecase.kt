package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchGroupUsecase @Inject constructor(
    private val repository: ShareGroupRepository
){
    suspend operator fun invoke(shareGroupId : Long) = flow {
        emit(repository.searchGroup(shareGroupId))
    }
}