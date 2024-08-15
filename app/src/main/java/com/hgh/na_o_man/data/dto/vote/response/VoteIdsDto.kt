package com.hgh.na_o_man.data.dto.vote.response

import com.hgh.na_o_man.domain.model.vote.VoteIdsModel

data class VoteIdsDto(
    val voteIdList: List<Long>
) {
    fun toModel() = VoteIdsModel(
        voteIdList = this.voteIdList
    )
}