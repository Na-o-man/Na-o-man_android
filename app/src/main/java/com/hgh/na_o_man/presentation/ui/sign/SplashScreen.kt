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
