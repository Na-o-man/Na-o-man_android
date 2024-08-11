package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel

data class PhotoUploadCountDto(
    val shareGroupId: Long,
    val uploadCount: Int
) {
    fun toModel() = PhotoUploadCountModel(
        shareGroupId = this.shareGroupId,
        uploadCount = this.uploadCount
    )
}