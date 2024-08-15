package com.hgh.na_o_man.data.dto.member.response

import com.hgh.na_o_man.domain.model.member.DeleteMemberModel

class DeleteMemberDto(
    val memberId: Long,
    val deletedAt: String
) {
    fun toModel() = DeleteMemberModel(
        memberId = this.memberId,
        deletedAt = this.deletedAt
    )
}