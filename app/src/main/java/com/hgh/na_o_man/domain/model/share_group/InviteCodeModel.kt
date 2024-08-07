package com.hgh.na_o_man.domain.model.share_group

data class InviteCodeModel(
    val shareGroupId: Int,
    val inviteCode: String,
    val inviteUrl: String
)