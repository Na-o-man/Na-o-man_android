package com.hgh.na_o_man.presentation.ui.sign

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.hgh.na_o_man.R
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.presentation.ui.main.MainActivity
import com.hgh.na_o_man.presentation.ui.sign.SignActivity.Companion.SIGN_ACTIVITY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    dateStoreUtil: DataStoreUtil,
    naviSignScreen: () -> Unit,
    naviUploadScreen: () -> Unit
) {
    val context = LocalContext.current as Activity
    if (context.intent.getBooleanExtra(SIGN_ACTIVITY, false)) naviUploadScreen()

    LaunchedEffect(Unit) {
        //커밋시 이부분 지우고 커밋
        //건희
        dateStoreUtil.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6IjM5Iiwicm9sZSI6IlJPTEVfREVGQVVMVCIsImlhdCI6MTcyNDMzMzM5NSwiZXhwIjoxNzI0OTM4MTk1fQ.XUOF9HApwVTjxbtk40IMX2FjH9Aur6IUepdfjImQye4")
        //강연

//        dateStoreUtil.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6IjEiLCJyb2xlIjoiUk9MRV9ERUZBVUxUIiwiaWF0IjoxNzI0MjQ1NzI2LCJleHAiOjE3MjQ4NTA1MjZ9._YV7aZ2tvyRKGLNe8cU96thRtI2L7749jXxoepJSFz4")
        
        //dateStoreUtil.setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImdoZWUzMjFAZ21haWwuY29tIiwicm9sZSI6IlJPTEVfREVGQVVMVCIsImlhdCI6MTcyMzM4NDk3OCwiZXhwIjoxNzIzOTg5Nzc4fQ.SI91aCKHgLhVzU8Of8OP0H_c6IDJ4NrFX9U9W4jgBdc")
        dateStoreUtil.setAutoLogin(false)


        delay(2000)
        if (dateStoreUtil.getAutoLogin()) {
            Log.d("엑세스", "${dateStoreUtil.getAccessToken()}")
            MainActivity.goMain(context)
        } else {
            naviSignScreen()
        }
    }

    Log.d("리컴포저블", "splashScreen")

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {

        Box(
            modifier = Modifier.background(Color(0xFFBBCFE5))
        ) {
            Image(
                painter = painterResource(R.drawable.background_login),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
