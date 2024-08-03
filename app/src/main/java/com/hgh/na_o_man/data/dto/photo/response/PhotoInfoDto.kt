package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel

data class PhotoInfoDto(
    val createdAt: String,
    val rawPhotoUrl: String,
    val w200PhotoUrl: String,
    val w400PhotoUrl: String
) {
    fun toModel() = PhotoInfoModel(
        createdAt = this.createdAt,
        rawPhotoUrl = this.rawPhotoUrl,
        w200PhotoUrl = this.w200PhotoUrl,
        w400PhotoUrl = this.w400PhotoUrl
    )
}