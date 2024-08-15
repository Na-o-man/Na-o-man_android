package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.ShareGroupNameListModel

data class ShareGroupNameListDto(
    val first: Boolean,
    val last: Boolean,
    val page: Int,
    val shareGroupNameInfoList: List<ShareGroupNameInfoDto>,
    val totalElements: Int
) {
    fun toModel() = ShareGroupNameListModel(
        first = this.first,
        last = this.last,
        page = this.page,
        shareGroupNameInfoList = this.shareGroupNameInfoList.map { it.toModel() },
        totalElements = this.totalElements
    )
}