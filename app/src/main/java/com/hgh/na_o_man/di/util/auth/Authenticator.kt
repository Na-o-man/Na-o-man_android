package com.hgh.na_o_man.di.util.auth

import android.content.Context
import android.content.Intent
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.presentation.ui.sign.SignActivity
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

            //토큰 재발급 api 구현 전 임시 로그인 화면으로 이동
            runBlocking {
                ds.setAutoLogin(false)
            }
            naviToLogin()

//            val newTokens = runCatching {
//                runBlocking {
//                   ds.getRefreshToken()
//                }
//            }.onSuccess {
//                runBlocking {
////                    ds.apply {
////                        ds.setRefreshToken()
////                    }
//                }
//            }.onFailure {
//                runBlocking {
//                    ds.setAutoLogin(false)
//                }
//                naviToLogin()
//            }.getOrThrow()
//
//            return response.request.newBuilder()
//                //.header("accessToken", newTokens.data?.accessToken ?: "")
//                .build()
        }
        return  null
    }
    private fun naviToLogin(){
        val intent = Intent(context, SignActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    companion object {
        const val CODE_TOKEN_EXPIRED = 401
    }
}