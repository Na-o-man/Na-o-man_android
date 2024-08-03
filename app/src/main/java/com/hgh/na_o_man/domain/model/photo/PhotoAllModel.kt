package com.hgh.na_o_man.domain.model.photo

import com.hgh.na_o_man.data.dto.photo.response.PhotoInfoDto

data class PhotoAllModel(
    val isFirst: Boolean,
    val isLast: Boolean,
    val photoInfoList: List<PhotoInfoDto>,
    val totalElements: Int,
    val totalPage: Int
)
