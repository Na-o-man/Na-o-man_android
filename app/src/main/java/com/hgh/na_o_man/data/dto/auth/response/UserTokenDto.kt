package com.hgh.na_o_man.data.dto.auth.response

import com.hgh.na_o_man.domain.model.auth.UserTokenModel

data class UserTokenDto(
    val accessToken: String,
    val memberId: Int,
    val refreshToken: String,
) {
    fun toModel() = UserTokenModel(
        memberId = this.memberId,
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )
}