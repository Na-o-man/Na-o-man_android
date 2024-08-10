package com.hgh.na_o_man.domain.model

data class VoteDummy(
    val id : Long,
    val title : String,
    val imageCount : Int,
    val images: List<Int>
)
