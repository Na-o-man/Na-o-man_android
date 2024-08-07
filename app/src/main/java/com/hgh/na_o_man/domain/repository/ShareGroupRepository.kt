package com.hgh.na_o_man.domain.repository

import android.adservices.adid.AdId
import android.provider.ContactsContract.CommonDataKinds.Email
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.DeleteResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.dto.share_group.response.InviteCodeResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.MyGroupListResponseDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.share_group.DeleteModel
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel
import com.hgh.na_o_man.domain.model.share_group.InviteCodeModel

interface ShareGroupRepository {
    suspend fun postShare(groupAddRequestDto: GroupAddRequestDto): RetrofitResult<GroupAddModel>
    suspend fun postJoin(groupJoinRequestDto: GroupJoinRequestDto) : RetrofitResult<GroupJoinModel>
    suspend fun searchGroup(shareGroupId : Long) : RetrofitResult<GroupAddModel>
    suspend fun getInviteCode(shareGroupId: Long): RetrofitResult<InviteCodeModel>
    suspend fun deleteGroup(shareGroupId: Long): RetrofitResult<DeleteModel>
    suspend fun getMyGroup(page: Int, size: Int): RetrofitResult<MyGroupListResponseDto>
}