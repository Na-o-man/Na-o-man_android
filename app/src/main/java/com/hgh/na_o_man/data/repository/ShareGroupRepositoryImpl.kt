package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.repository.ShareGroupRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
 class ShareGroupRepositoryImpl @Inject constructor(
    private val api: ShareGroupsService
) : ShareGroupRepository {

    override suspend fun postShare(groupAddRequestDto: GroupAddRequestDto): RetrofitResult<GroupAddModel> {
        return apiHandler({ api.postAddGroup(groupAddRequestDto)}) { response: ApiResult<GroupAddDto> -> response.data.toModel() }
    }

}