package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.InviteCodeModel

data class InviteCodeResponseDto(
    val shareGroupId: Int,
    val inviteCode: String,
    val inviteUrl: String
) {
    fun toModel() = InviteCodeModel(
        shareGroupId = this.shareGroupId,
        inviteCode = this.inviteCode,
        inviteUrl = this.inviteUrl
    )
}