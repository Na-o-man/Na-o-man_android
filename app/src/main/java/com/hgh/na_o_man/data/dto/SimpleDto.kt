package com.hgh.na_o_man.data.dto

import com.hgh.na_o_man.domain.model.SimpleModel

data class SimpleDto(
    val id: Int,
) {
    fun toModel() = SimpleModel(
        id = this.id
    )
}
