package com.hgh.na_o_man.data.dto.vote.response

import com.hgh.na_o_man.domain.model.vote.VoteDetailModel

data class VoteDetailDto(
    val agendaPhotoId: Long,
    val voteCount: Int,
    val voteInfoList: List<VoteInfoDto>
) {
    fun toModel() = VoteDetailModel(
        agendaPhotoId = this.agendaPhotoId,
        voteCount = this.voteCount,
        voteInfoList = this.voteInfoList.map { it.toModel() }
    )
}