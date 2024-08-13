package com.hgh.na_o_man.domain.model.vote

data class VoteDetailModel(
    val agendaPhotoId: Long,
    val voteCount: Int,
    val voteInfoList: List<VoteInfoModel>
)