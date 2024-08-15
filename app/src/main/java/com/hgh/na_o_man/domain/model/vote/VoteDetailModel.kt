package com.hgh.na_o_man.domain.model.vote

import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel

data class VoteDetailModel(
    val agendaPhotoId: Long = -1L,
    val photoInfo: PhotoInfoModel = PhotoInfoModel(),
    val voteCount: Int = 0,
    val voteInfoList: List<VoteInfoModel> = listOf()
)