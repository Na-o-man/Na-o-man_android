package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.GroupAddModel

data class GroupShareInfoDto(
    val shareGroupId: Int,
    val name: String,
    val image: String,
    val memberCount: Int,
    val inviteUrl: String,
    val createdAt: String,
    val profileInfoList: List<ProfileInfoDto>
) {
    fun toModel() = GroupAddModel(
        shareGroupId = this.shareGroupId,
        name = this.name,
        image = this.image,
        memberCount = this.memberCount,
        inviteUrl = this.inviteUrl,
        createdAt = this.createdAt,
        profileInfoList = this.profileInfoList.map { it.toModel() }
    )
}