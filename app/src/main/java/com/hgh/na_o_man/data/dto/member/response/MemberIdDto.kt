package com.hgh.na_o_man.data.dto.member.response

import com.hgh.na_o_man.domain.model.member.MemberIdModel

data class MemberIdDto(
    val memberId: Long
) {
    fun toModel() = MemberIdModel (
        memberId = this.memberId
    )
}