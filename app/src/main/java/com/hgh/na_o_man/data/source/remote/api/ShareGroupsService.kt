package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.share_group.request.GroupAddRequestDto
import com.hgh.na_o_man.data.dto.share_group.request.GroupJoinRequestDto
import com.hgh.na_o_man.data.dto.share_group.response.DeleteResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupAddDto
import com.hgh.na_o_man.data.dto.share_group.response.GroupJoinDto
import com.hgh.na_o_man.data.dto.share_group.response.InviteCodeResponseDto
import com.hgh.na_o_man.data.dto.share_group.response.MyGroupListResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShareGroupsService {
    @POST("shareGroups")
    suspend fun postAddGroup(
        @Body addGroupAddRequest: GroupAddRequestDto
    ) : Response<ApiResult<GroupAddDto>>

    @POST("shareGroups/join")
    suspend fun postJoinGroup(
        @Body joinGroupJoinRequest: GroupJoinRequestDto
    ) : Response<ApiResult<GroupJoinDto>>

    @GET("shareGroups/{shareGroupId}")
    suspend fun groupSearch(
        @Path("shareGroupId") shareGroupId : Long // 맞는지 확인해야 함
    ) : Response<ApiResult<GroupAddDto>> // 재활용 가능한지 확인

    @GET("shareGroups/inviteCode/{shareGroupId}")
    suspend fun getInviteCode(
        @Path("shareGroupId") shareGroupId: Long
    ): Response<ApiResult<InviteCodeResponseDto>>

    @DELETE("shareGroups/{shareGroupId}")
    suspend fun deleteGroup(
        @Path("shareGroupId") shareGroupId: Long
    ): Response<ApiResult<DeleteResponseDto>>

    @GET("shareGroups/my")
    suspend fun getMyGroup(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ApiResult<MyGroupListResponseDto>>
}