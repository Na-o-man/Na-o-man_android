package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PreSignedUrlInfoModel

data class PreSignedUrlInfoDto(
    val photoName: String,
    val photoUrl: String,
    val preSignedUrl: String
) {
    fun toModel() = PreSignedUrlInfoModel(
        photoName = this.photoName,
        photoUrl = this.photoUrl,
        preSignedUrl = this.preSignedUrl
    )
}