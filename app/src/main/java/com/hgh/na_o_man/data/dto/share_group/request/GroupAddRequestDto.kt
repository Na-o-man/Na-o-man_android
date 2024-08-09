package com.hgh.na_o_man.data.dto.share_group.request

import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel

data class GroupAddRequestDto(
    val meetingTypeList: List<String>,
    val memberCount: Int,
    val memberNameList: List<String>,
    val place: String
)