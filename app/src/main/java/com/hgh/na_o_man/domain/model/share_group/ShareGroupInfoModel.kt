package com.hgh.na_o_man.domain.model.share_group

data class ShareGroupInfoModel(
    val createdAt: String,
    val image: String,
    val inviteUrl: String,
    val memberCount: Int,
    val name: String,
    val shareGroupId: Int
)
