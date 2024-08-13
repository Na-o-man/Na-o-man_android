package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.ShareGroupNameListDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel
import com.hgh.na_o_man.domain.model.share_group.GroupListReferModel
import com.hgh.na_o_man.domain.model.share_group.CheckSpecificGroupModel
import com.hgh.na_o_man.domain.model.share_group.GroupInviteModel
import com.hgh.na_o_man.domain.model.share_group.ShareGroupNameListModel

interface ShareGroupRepository {
    suspend fun postShare(groupAddRequestDto: GroupAddRequestDto): RetrofitResult<GroupAddModel>
    suspend fun postJoin(groupJoinRequestDto: GroupJoinRequestDto) : RetrofitResult<GroupJoinModel>
    suspend fun searchGroup(shareGroupId : Long) : RetrofitResult<GroupAddModel>
    suspend fun referGroup(page : Int, size : Int) : RetrofitResult<GroupListReferModel>
    suspend fun checkSpecificGroup(shareGroupId : Long) : RetrofitResult<CheckSpecificGroupModel>
    suspend fun getInvite(shareGroupId: String): RetrofitResult<GroupInviteModel>
    suspend fun getShareGroupNameList(page: Int,size: Int) : RetrofitResult<ShareGroupNameListModel>
}