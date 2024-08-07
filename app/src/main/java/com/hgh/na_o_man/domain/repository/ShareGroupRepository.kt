package com.hgh.na_o_man.domain.repository

import android.adservices.adid.AdId
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel
import com.hgh.na_o_man.domain.model.share_group.GroupListReferModel

interface ShareGroupRepository {
    suspend fun postShare(groupAddRequestDto: GroupAddRequestDto): RetrofitResult<GroupAddModel>
    suspend fun postJoin(groupJoinRequestDto: GroupJoinRequestDto) : RetrofitResult<GroupJoinModel>
    suspend fun searchGroup(shareGroupId : Long) : RetrofitResult<GroupAddModel>
    suspend fun referGroup(page : Int, size : Int) : RetrofitResult<GroupListReferModel>
}