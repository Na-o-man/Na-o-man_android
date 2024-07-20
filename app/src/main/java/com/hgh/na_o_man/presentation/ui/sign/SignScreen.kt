package com.hgh.na_o_man.presentation.ui.sign

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.Utill.SocialLoginUtil

@Composable
fun SignScreen(
    viewModel: SignViewModel = hiltViewModel(),
) {
    val context = LocalContext.current as Activity
    val socialLoginUtil = remember {
        SocialLoginUtil(context, object : SocialLoginUtil.LoginCallback {
            override fun onLoginSuccess(token: String) {
                Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
            }

            override fun onLoginFailure(error: Throwable) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                // 에러 처리 로직
            }
        })
    }
    val googleLoginLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("구글", "로그인 스타트 ")
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("구글", "로그인 0")
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            socialLoginUtil.handleGoogleSignInResult(task)
        } else {
            Log.d("구글", "로그인 실패 ${result.resultCode}")
        }
    }



    Surface (
        modifier = Modifier.fillMaxSize()
    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_login_with_google_222),
                contentDescription = "구글 로그인",
                modifier = Modifier.clickable {
                    val signInIntent = socialLoginUtil.googleSignIn()
                    googleLoginLauncher.launch(signInIntent)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_login_with_kakao_222),
                contentDescription = "카톡 로그인",
                modifier = Modifier.clickable {
                    socialLoginUtil.kakaoSignIn()
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface (
        modifier = Modifier.fillMaxSize()

    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_login_with_google_222),
                contentDescription = "구글 로그인",
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_login_with_kakao_222),
                contentDescription = "카톡 로그인",
            )
        }
    }
}