package com.hgh.na_o_man.domain.model.share_group

data class GroupListReferModel(
    val first: Boolean,
    val last: Boolean,
    val page: Int,
    val shareGroupInfoList: List<ShareGroupInfoModel>,
    val totalElements: Int
)
