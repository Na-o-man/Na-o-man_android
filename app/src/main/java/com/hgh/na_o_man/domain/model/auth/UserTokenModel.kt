package com.hgh.na_o_man.domain.model.auth

data class UserTokenModel(
    val accessToken: String,
    val memberId: Int,
    val refreshToken: String
)