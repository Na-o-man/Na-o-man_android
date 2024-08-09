package com.hgh.na_o_man.domain.model.photo

data class PhotoInfoModel(
    val photoId: Long = -1,
    val createdAt: String = "",
    val rawPhotoUrl: String = "",
    val w200PhotoUrl: String = "",
    val w400PhotoUrl: String = "",
    val isSelected: Boolean = false,
    val isDownloaded: Boolean = false,
    val isVoted: Boolean = false,
) {
    constructor() : this(-1, "", "", "", "")
}