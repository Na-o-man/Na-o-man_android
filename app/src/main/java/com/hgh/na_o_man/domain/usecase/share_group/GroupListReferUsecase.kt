package com.hgh.na_o_man.domain.usecase.share_group

import android.util.Log
import com.hgh.na_o_man.data.dto.share_group.response.GroupListReferDto
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GroupListReferUsecase @Inject constructor(
    private val repository: ShareGroupRepository

) {
    suspend operator fun invoke(page : Int, size : Int) = flow {
        Log.d("GroupListReferUsecase", "Invoking referGroup with page: $page, size: $size")
        emit(repository.referGroup(page, size))
    }
}