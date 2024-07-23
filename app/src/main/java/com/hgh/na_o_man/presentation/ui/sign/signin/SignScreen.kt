package com.hgh.na_o_man.presentation.ui.sign.signin

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListContract
import com.hgh.na_o_man.presentation.util.SocialLoginUtil

@Composable
fun SignScreen(
    naviAgreeScreen: () -> Unit,
    viewModel: SignViewModel = hiltViewModel(),
) {
    val context = LocalContext.current as Activity
    val socialLoginUtil = remember {
        SocialLoginUtil(context, object : SocialLoginUtil.LoginCallback {
            override fun onLoginSuccess(dummy: Dummy) {
                viewModel.setEvent(SignContract.SignEvent.OnClickLogin(dummy))
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
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            socialLoginUtil.handleGoogleSignInResult(task)
        }
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SignContract.SignSideEffect.NaviAgree -> {
                    naviAgreeScreen()
                }

                is SignContract.SignSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    Log.d("리컴포저블", "signScreen")

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_login_with_google_222),
                    contentDescription = "구글 로그인",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                        .aspectRatio(4.3F)
                        .clickable {
                            val signInIntent = socialLoginUtil.googleSignIn()
                            googleLoginLauncher.launch(signInIntent)
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_login_with_kakao_222),
                    contentDescription = "카톡 로그인",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                        .aspectRatio(4.3F)
                        .clickable {
                            socialLoginUtil.kakaoSignIn()
                        }
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomCenter)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_login_with_google_222),
                    contentDescription = "구글 로그인",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                        .aspectRatio(4.3F)
                        .clickable {
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_login_with_kakao_222),
                    contentDescription = "카톡 로그인",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                        .aspectRatio(4.3F)
                        .clickable {
                        }
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}