package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoDownloadUrlsModel
import com.hgh.na_o_man.domain.model.photo.PhotoPreSignedModel
import com.hgh.na_o_man.domain.model.photo.PhotoUploadCountModel

data class PhotoDownloadUrlsDto(
    val photoDownloadUrlList: List<String>
) {
    fun toModel() = PhotoDownloadUrlsModel(
         downloadUrls =this.photoDownloadUrlList
    )
}