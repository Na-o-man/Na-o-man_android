package com.hgh.na_o_man.data.dto.auth.request

data class SignUpRequestDto(
    val authId: String,
    val email: String,
    val image: String,
    val marketingAgreed: Boolean,
    val name: String,
    val socialType: String
)