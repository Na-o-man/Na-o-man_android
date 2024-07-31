package com.hgh.na_o_man.domain.model.share_group

data class GroupAddModel(
    val createdAt: String,
    val image: String,
    val inviteUrl: String,
    val memberCount: Int,
    val name: String,
    val profileInfoList: List<ProfileInfoModel>,
    val shareGroupId: Int
)