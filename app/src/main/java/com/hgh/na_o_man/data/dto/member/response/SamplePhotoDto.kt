package com.hgh.na_o_man.data.dto.member.response

import com.hgh.na_o_man.domain.model.member.SamplePhotoModel

data class SamplePhotoDto (
    val hasSamplePhoto: Boolean
) {
    fun toModel() = SamplePhotoModel(
        hasSamplePhoto = this.hasSamplePhoto
    )
}