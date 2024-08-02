package com.hgh.na_o_man.domain.model.photo

data class PreSignedUrlInfoModel(
    val photoName: String,
    val photoUrl: String,
    val preSignedUrl: String
)
