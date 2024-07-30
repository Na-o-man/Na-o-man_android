package com.hgh.na_o_man.domain.model.auth

data class AuthInfoModel(
    val authId: String = "",
    val socialType: String = "",
    val email: String = "",
    val profileUrl: String = "",
    val name: String = "",
)
