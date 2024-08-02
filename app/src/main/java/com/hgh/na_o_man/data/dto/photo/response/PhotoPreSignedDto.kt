package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoPreSignedModel
import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel

data class PhotoPreSignedDto(
    val preSignedUrlInfoList: List<PreSignedUrlInfoDto>
) {
    fun toModel() = PhotoPreSignedModel(
        preSignedUrlInfoList = this.preSignedUrlInfoList
    )
}