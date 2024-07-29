package com.hgh.na_o_man.di.util.auth

import android.content.Context
import com.hgh.na_o_man.di.util.DataStoreUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val ds: DataStoreUtil,
    @ApplicationContext private val context: Context,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == CODE_TOKEN_EXPIRED) {
            val newTokens = runCatching {
                runBlocking {
                   ds.getRefreshToken()
                }
            }.onSuccess {
                runBlocking {
//                    ds.apply {
//                        setAccessToken(it.data?.accessToken ?: "")
//                    }
                }
            }.onFailure {
                runBlocking {
                    ds.setAutoLogin(false)
                }
                //ProcessPhoenix.triggerRebirth(context)
            }.getOrThrow()

            return response.request.newBuilder()
                //.header("accessToken", newTokens.data?.accessToken ?: "")
                .build()
        }
        return  null
    }

    companion object {
        const val CODE_TOKEN_EXPIRED = 401
    }
}