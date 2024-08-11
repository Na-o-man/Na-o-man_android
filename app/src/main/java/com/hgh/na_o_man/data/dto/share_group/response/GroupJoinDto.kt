package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.GroupJoinModel

data class GroupJoinDto(
    val shareGroupId: Long
) {
    fun toModel() = GroupJoinModel(
        shareGroupId = this.shareGroupId
    )
}