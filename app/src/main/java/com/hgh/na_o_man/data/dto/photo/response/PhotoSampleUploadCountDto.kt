package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel

data class PhotoSampleUploadCountDto(
    val memberId: Long,
    val uploadCount: Int
) {
    fun toModel() = PhotoUploadCountModel(
        shareGroupId = this.memberId,
        uploadCount = this.uploadCount
    )
}