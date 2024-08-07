package com.hgh.na_o_man.data.dto.share_group.response

data class MyGroupListResponseDto(
    val shareGroupInfoList: List<GroupShareInfoDto>,
    val page: Int,
    val totalElements: Int,
    val first: Boolean,
    val last: Boolean
)
