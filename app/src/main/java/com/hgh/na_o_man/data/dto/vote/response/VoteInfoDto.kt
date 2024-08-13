package com.hgh.na_o_man.data.dto.vote.response

import com.hgh.na_o_man.data.dto.share_group.response.ProfileInfoDto
import com.hgh.na_o_man.domain.model.vote.VoteInfoModel

data class VoteInfoDto(
    val agendaPhotoId: Long,
    val comment: String,
    val isMine: Boolean,
    val profileInfo: ProfileInfoDto,
    val voteId: Long,
    val votedAt: String
) {
    fun toModel() = VoteInfoModel(
        agendaPhotoId = this.agendaPhotoId,
        comment = this.comment,
        isMine = this.isMine,
        profileInfo = this.profileInfo.toModel(),
        voteId = this.voteId,
        votedAt = this.votedAt,
    )
}