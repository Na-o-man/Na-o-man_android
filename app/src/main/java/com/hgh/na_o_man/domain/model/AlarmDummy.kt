package com.hgh.na_o_man.domain.model

data class AlarmDummy(
    val url : String,
    val imageRes : Int,
    val detail : String,
    val date: String,
    val isRead: Boolean = false
)
