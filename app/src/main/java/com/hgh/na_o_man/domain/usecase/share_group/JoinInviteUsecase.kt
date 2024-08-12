package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JoinInviteUsecase @Inject constructor(
    private val repository: ShareGroupRepository
) {
    suspend operator fun invoke(inviteCode: String) = flow {
        val response = repository.getInvite(inviteCode)
        emit(response)
    }
}
