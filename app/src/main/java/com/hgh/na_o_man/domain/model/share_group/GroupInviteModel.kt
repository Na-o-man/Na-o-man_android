package com.hgh.na_o_man.domain.model.share_group

data class GroupInviteModel(
    val shareGroupId: Int,
    val createdAt: String,
    val image: String?,
    val inviteUrl: String,
    val inviteCode: String,
    val memberCount: Int,
    val name: String,
    val profileInfoList: List<ProfileInfoModel> // 리스트 형태로 정의
)