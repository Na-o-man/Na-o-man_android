package com.hgh.na_o_man.domain.model.share_group

data class ShareGroupNameListModel(
    val first: Boolean,
    val last: Boolean,
    val page: Int,
    val shareGroupNameInfoList: List<ShareGroupNameInfoModel>,
    val totalElements: Int
)
