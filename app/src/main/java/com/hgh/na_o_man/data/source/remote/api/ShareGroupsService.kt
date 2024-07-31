package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ShareGroupsService {
    @POST("shareGroups")
    suspend fun postAddGroup(
        @Body addGroupAddRequest: GroupAddRequestDto
    ) : Response<ApiResult<GroupAddDto>>
}