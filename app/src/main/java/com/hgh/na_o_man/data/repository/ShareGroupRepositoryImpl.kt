package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.DeleteResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupJoinDto
import com.hgh.na_o_man.data.dto.share_group.response.InviteCodeResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.MyGroupListResponseDto
import com.hgh.na_o_man.data.source.remote.api.ShareGroupsService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.share_group.DeleteModel
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel
import com.hgh.na_o_man.domain.model.share_group.InviteCodeModel
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

    override suspend fun getInviteCode(shareGroupId: Long): RetrofitResult<InviteCodeModel> {
        return apiHandler({ api.getInviteCode(shareGroupId) }) { response: ApiResult<InviteCodeResponseDto> ->
            response.data.toModel()
        }
    }

    override suspend fun deleteGroup(shareGroupId: Long): RetrofitResult<DeleteModel> {
        return apiHandler({ api.deleteGroup(shareGroupId) }) { response: ApiResult<DeleteResponseDto> ->
            response.data.toModel()
        }
    }

    override suspend fun getMyGroup(page: Int, size: Int): RetrofitResult<MyGroupListResponseDto> {
        return apiHandler({ api.getMyGroup(page, size) }) { response: ApiResult<MyGroupListResponseDto> ->
            response.data
        }
    }
}