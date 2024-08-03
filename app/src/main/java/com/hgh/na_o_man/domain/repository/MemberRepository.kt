package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.member.MarketingAgreedModel
import com.hgh.na_o_man.domain.model.member.SearchSuccessModel

interface MemberRepository {
    suspend fun getMemberId(memberId : Long) : RetrofitResult<SearchSuccessModel>
    suspend fun getMarketingAgreed(memberId: Long) : RetrofitResult<MarketingAgreedModel>
}