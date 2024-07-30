package com.hgh.na_o_man.data.dto

data class ApiResult<T>(
    val status: Int,
    val code: String,
    val message: String,
    val data: T
)