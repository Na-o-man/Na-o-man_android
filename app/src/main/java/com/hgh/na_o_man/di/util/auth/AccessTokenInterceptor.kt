package com.hgh.na_o_man.di.util.auth

import android.util.Log
import com.hgh.na_o_man.di.util.DataStoreUtil
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val ds : DataStoreUtil
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val jwt: String? = runBlocking { ds.getAccessToken() }
        jwt?.let {
            builder.addHeader("Authorization", "Bearer $jwt")
            Log.d("엑세스",jwt )
        }
        return chain.proceed(builder.build())
    }
}