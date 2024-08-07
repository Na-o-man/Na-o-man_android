package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.DeleteModel

data class DeleteResponseDto(
    val shareGroupId: Int
) {
    fun toModel() = DeleteModel(
        shareGroupId = this.shareGroupId
    )
}