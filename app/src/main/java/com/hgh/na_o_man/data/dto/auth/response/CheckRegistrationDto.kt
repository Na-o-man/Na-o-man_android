package com.hgh.na_o_man.data.dto.auth.response

import com.hgh.na_o_man.domain.model.auth.CheckRegistrationModel

data class CheckRegistrationDto(
    val isRegistered: Boolean,
) {
    fun toModel() = CheckRegistrationModel(
        isRegistered = this.isRegistered,
    )
}