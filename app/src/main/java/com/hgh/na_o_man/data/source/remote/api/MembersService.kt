package com.hgh.na_o_man.data.source.remote.api

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.member.response.MarketingAgreedDto
import com.hgh.na_o_man.data.dto.member.response.MemberIdDto
import com.hgh.na_o_man.data.dto.member.response.SearchSuccessDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MembersService {
    @GET("members/{memberId}")
    suspend fun getMemberIdAPI(
        @Path("memberId") memberId: Long
    ): Response<ApiResult<SearchSuccessDto>>

    @GET("members/terms/{memberId}")
    suspend fun getMarketingAgreedAPI(
        @Path("memberId") memberId: Long
    ): Response<ApiResult<MarketingAgreedDto>>

    @GET("members/my")
    suspend fun getMyInfoAPI(): Response<ApiResult<SearchSuccessDto>>

    @GET("members/my-memberId")
    suspend fun getMyIdAPI(): Response<ApiResult<MemberIdDto>>
}