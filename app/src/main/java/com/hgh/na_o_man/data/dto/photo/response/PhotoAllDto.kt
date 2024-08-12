package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoAllModel

data class PhotoAllDto(
    val isFirst: Boolean,
    val isLast: Boolean,
    val photoInfoList: List<PhotoInfoDto>,
    val totalElements: Int,
    val totalPages: Int
) {
    fun toModel() = PhotoAllModel(
        isFirst = this.isFirst,
        isLast = this.isLast,
        photoInfoList = this.photoInfoList.map { it.toModel() },
        totalElements = this.totalElements,
        totalPage = this.totalPages
    )
}