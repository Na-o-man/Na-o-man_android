package com.hgh.na_o_man.data.dto.share_group.response

import com.hgh.na_o_man.domain.model.share_group.GroupAddModel
import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel

data class ProfileInfoDto(
    val image: String,
    val memberId: Long,
    val name: String,
    val profileId: Long
) {
    fun toModel() = ProfileInfoModel(
        image = this.image,
        memberId = this.memberId,
        name = this.name,
        profileId = this.profileId
    )
}