package com.hgh.na_o_man.domain.model.vote

import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel

data class VoteInfoModel(
    val agendaPhotoId: Long,
    val comment: String,
    val isMine: Boolean,
    val profileInfo: ProfileInfoModel,
    val voteId: Long,
    val votedAt: String
)