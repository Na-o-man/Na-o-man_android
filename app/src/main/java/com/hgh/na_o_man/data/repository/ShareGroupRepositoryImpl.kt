package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupJoinDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupListReferDto
import com.hgh.na_o_man.data.dto.share_group.response.CheckSpecificGroupDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupInviteDto
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel
import com.hgh.na_o_man.domain.model.share_group.GroupListReferModel
import com.hgh.na_o_man.domain.model.share_group.CheckSpecificGroupModel
import com.hgh.na_o_man.domain.model.share_group.GroupInviteModel
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

    override suspend fun postJoin(groupJoinRequestDto: GroupJoinRequestDto): RetrofitResult<GroupJoinModel> {
        return apiHandler({ api.postJoinGroup(groupJoinRequestDto)}) { response: ApiResult<GroupJoinDto> -> response.data.toModel() }
    }

    override suspend fun searchGroup(shareGroupId: Long): RetrofitResult<GroupAddModel> {
        return apiHandler({ api.groupSearch(shareGroupId)}) { response: ApiResult<GroupAddDto> -> response.data.toModel() }
    }

    override suspend fun referGroup(page: Int, size: Int): RetrofitResult<GroupListReferModel> {
        return apiHandler({ api.groupListRefer(page, size)}) { response : ApiResult<GroupListReferDto> -> response.data.toModel()}
    }

    override suspend fun checkSpecificGroup(shareGroupId: Long): RetrofitResult<CheckSpecificGroupModel> {
        return apiHandler({ api.checkSpecificGroup(shareGroupId)}) { response : ApiResult<CheckSpecificGroupDto> -> response.data.toModel()}
    }

    override suspend fun getInvite(inviteCode: String): RetrofitResult<GroupInviteModel> {
        return apiHandler({ api.getInvite(inviteCode) }) { response: ApiResult<GroupInviteDto> -> response.data.toModel()}
    }

}