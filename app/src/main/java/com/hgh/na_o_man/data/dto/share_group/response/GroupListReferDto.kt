package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.GroupListReferModel

data class GroupListReferDto(
    val first: Boolean,
    val last: Boolean,
    val page: Int,
    val shareGroupInfoList: List<ShareGroupInfoDto>,
    val totalElements: Int
) {
    fun toModel() = GroupListReferModel(
        first = this.first,
        last = this.last,
        page = this.page,
        shareGroupInfoList = this.shareGroupInfoList.map{it.toModel()},
        totalElements = this.totalElements
    )
}