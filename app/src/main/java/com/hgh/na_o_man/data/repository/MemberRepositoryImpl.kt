package com.hgh.na_o_man.data.repository

import com.hgh.na_o_man.data.dto.ApiResult
import com.hgh.na_o_man.data.dto.member.response.MarketingAgreedDto
import com.hgh.na_o_man.data.dto.member.response.SearchSuccessDto
import com.hgh.na_o_man.data.source.remote.api.MembersService
import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.di.util.remote.apiHandler
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.domain.model.member.MarketingAgreedModel
import com.hgh.na_o_man.domain.model.member.SearchSuccessModel
import com.hgh.na_o_man.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val api : MembersService
) : MemberRepository{

    override suspend fun getMemberId(memberId: Long): RetrofitResult<SearchSuccessModel> {
        return apiHandler({api.getMemberIdAPI(memberId)}) {response: ApiResult<SearchSuccessDto> -> response.data.toModel()}
    }

    override suspend fun getMarketingAgreed(memberId: Long): RetrofitResult<MarketingAgreedModel> {
        return apiHandler({api.getMarketingAgreedAPI(memberId)}) {response: ApiResult<MarketingAgreedDto> -> response.data.toModel()}
    }

    override suspend fun getMyInfo(): RetrofitResult<AuthInfoModel> {
        return apiHandler({api.getMyInfoAPI()}) {response: ApiResult<SearchSuccessDto> -> response.data.toAuthModel()}
    }

}