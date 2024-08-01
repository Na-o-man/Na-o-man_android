package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupJoinDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ShareGroupsService {
    @POST("shareGroups")
    suspend fun postAddGroup(
        @Body addGroupAddRequest: GroupAddRequestDto
    ) : Response<ApiResult<GroupAddDto>>

    @POST("shareGroups/join")
    suspend fun postJoinGroup(
        @Body joinGroupJoinRequest: GroupJoinRequestDto
    ) : Response<ApiResult<GroupJoinDto>>
}