package com.hgh.na_o_man.domain.repository

import com.hgh.na_o_man.di.util.remote.RetrofitResult
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.domain.model.member.DeleteMemberModel
import com.hgh.na_o_man.domain.model.member.MarketingAgreedModel
import com.hgh.na_o_man.domain.model.member.SamplePhotoModel
import com.hgh.na_o_man.domain.model.member.SearchSuccessModel

interface MemberRepository {
    suspend fun getMemberId(memberId: Long): RetrofitResult<SearchSuccessModel>
    suspend fun getMarketingAgreed(memberId: Long): RetrofitResult<MarketingAgreedModel>
    suspend fun getSample(): RetrofitResult<SamplePhotoModel>
    suspend fun getMyInfo(): RetrofitResult<AuthInfoModel>
    suspend fun deleteMember(): RetrofitResult<DeleteMemberModel>
}