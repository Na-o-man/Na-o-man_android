package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.ShareGroupNameInfoModel

data class ShareGroupNameInfoDto(
    val name: String,
    val shareGroupId: Long
) {
    fun toModel() = ShareGroupNameInfoModel(
        name = this.name,
        shareGroupId = this.shareGroupId
    )
}