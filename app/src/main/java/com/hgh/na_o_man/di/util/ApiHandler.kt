package com.hgh.na_o_man.di.util

import com.hgh.na_o_man.MainApplication
import com.hgh.na_o_man.di.RemoteModule
import retrofit2.Response

suspend fun <T : Any, R : Any> apiHandler(
    execute: suspend () -> Response<T>,
    mapper: (T) -> R
): RetrofitResult<R> {
    if (MainApplication.isOnline().not()) {
        return RetrofitResult.Error(Exception(RemoteModule.NETWORK_EXCEPTION_OFFLINE_CASE))
    }

    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                RetrofitResult.Success(mapper(it))
            } ?: run {
                throw NullPointerException(RemoteModule.NETWORK_EXCEPTION_BODY_IS_NULL)
            }
        } else {
            getFailRetrofitResult(body, response)
        }
    } catch (e: Exception) {
        RetrofitResult.Error(e)
    }
}


private fun <T : Any> getFailRetrofitResult(body: T?, response: Response<T>) = body?.let {
    RetrofitResult.Fail(statusCode = response.code(), message = it.toString())
} ?: run {
    RetrofitResult.Fail(statusCode = response.code(), message = response.message())
}