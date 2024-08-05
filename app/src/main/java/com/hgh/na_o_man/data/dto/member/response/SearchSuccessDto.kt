package com.hgh.na_o_man.data.dto.member.response

import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.domain.model.member.SearchSuccessModel

data class SearchSuccessDto(
    val email: String,
    val image: String,
    val name: String
) {
    fun toModel() = SearchSuccessModel(
        email = this.email,
        image = this.image,
        name = this.name
    )
    fun toAuthModel() = AuthInfoModel(
        email = this.email,
        profileUrl = this.image,
        name = this.name
    )
}