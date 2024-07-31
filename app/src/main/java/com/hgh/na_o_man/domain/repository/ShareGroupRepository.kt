package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel

interface ShareGroupRepository {
    suspend fun postShare(groupAddRequestDto: GroupAddRequestDto): RetrofitResult<GroupAddModel>
}