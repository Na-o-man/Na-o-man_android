package com.hgh.na_o_man.domain.usecase.share_group

import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShareGroupNameListUsecase @Inject constructor(
    private val repository: ShareGroupRepository
) {
    suspend operator fun invoke(page : Int, size : Int) = flow {
        emit(repository.getShareGroupNameList(page, size))
    }
}