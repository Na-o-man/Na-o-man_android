package com.hgh.na_o_man.domain.model.photo

data class PhotoAllModel(
    val isFirst: Boolean,
    val isLast: Boolean,
    val photoInfoList: List<PhotoInfoModel>,
    val totalElements: Int,
    val totalPage: Int
)
