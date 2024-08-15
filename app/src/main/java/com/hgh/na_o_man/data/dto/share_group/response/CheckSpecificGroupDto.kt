package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.CheckSpecificGroupModel

data class CheckSpecificGroupDto(
    val createdAt: String,
    val image: String,
    val inviteUrl: String,
    val memberCount: Int,
    val name: String,
    val profileInfoList: List<ProfileInfoDto>,
    val shareGroupId: Long
) {
    fun toModel() = CheckSpecificGroupModel(
        createdAt = this.createdAt,
        image = this.image,
        inviteUrl = this.inviteUrl,
        memberCount = this.memberCount,
        name = this.name,
        profileInfoList = this.profileInfoList.map { it.toModel() },
        shareGroupId = this.shareGroupId
    )
}