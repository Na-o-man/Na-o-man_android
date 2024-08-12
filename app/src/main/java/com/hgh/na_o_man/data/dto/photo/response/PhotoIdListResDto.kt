package com.hgh.na_o_man.data.dto.photo.response

import com.hgh.na_o_man.domain.model.photo.PhotoIdListResModel

data class PhotoIdListResDto(
    val photoIdList: List<Long>
) {
    fun toModel() = PhotoIdListResModel(
        photoIdList = this.photoIdList
    )
}
