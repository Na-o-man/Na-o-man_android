package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.GroupInviteModel

data class GroupInviteDto(
    val shareGroupId: Int,
    val createdAt: String,
    val image: String?,
    val inviteUrl: String?,
    val inviteCode: String?,
    val memberCount: Int,
    val name: String,
    val profileInfoList: List<ProfileInfoDto> // 리스트 형태로 정의
) {
    fun toModel() = GroupInviteModel(
        shareGroupId = this.shareGroupId,
        createdAt = this.createdAt,
        image = this.image,
        inviteUrl = this.inviteUrl ?: "",
        inviteCode = this.inviteCode ?: "",
        memberCount = this.memberCount,
        name = this.name,
        profileInfoList = this.profileInfoList.map { it.toModel() },
    )
}