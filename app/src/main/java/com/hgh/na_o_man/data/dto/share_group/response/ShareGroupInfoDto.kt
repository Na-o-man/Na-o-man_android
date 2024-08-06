package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.ShareGroupInfoModel

data class ShareGroupInfoDto(
    val createdAt: String,
    val image: String,
    val inviteUrl: String,
    val memberCount: Int,
    val name: String,
    val shareGroupId: Int
) {
    fun toModel() = ShareGroupInfoModel(
        createdAt = this.createdAt,
        image = this.image,
        inviteUrl = this.inviteUrl,
        memberCount = this.memberCount,
        name = this.name,
        shareGroupId = this.shareGroupId
    )
}