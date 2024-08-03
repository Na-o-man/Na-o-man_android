package com.hgh.na_o_man.presentation.ui.main.mypage

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.auth.AuthInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.CommonDialog
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.MyPageBtn
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.userIcon.UserProfile

@Composable
fun MyPageScreen(
    navigationBack: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    Log.d("리컴포저블", "MyPageScreen")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                MyPageContract.MyPageSideEffect.NaviBack -> {
                    navigationBack()
                }

                is MyPageContract.MyPageSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                MyPageContract.MyPageSideEffect.LogOut -> {

                }

                MyPageContract.MyPageSideEffect.SignOut -> {

                }
            }
        }
    }

    BackHandler(enabled = viewState.isDialogVisible) {
        viewModel.setEvent(MyPageContract.MyPageEvent.OnDialogClosed)
    }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Scaffold(
                topBar = {
                    StartAppBar(
                        onStartClick = {
                            viewModel.setEvent(MyPageContract.MyPageEvent.OnClickBack)
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    StartTopCloud()
                    DecorationCloud(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 62.dp)
                    )
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    UserProfile(userInfo = AuthInfoModel(), modifier = Modifier.size(160.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "로고 이미지",
                        modifier = Modifier
                            .width(200.dp)
                            .height(90.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    MyPageBtn(onClick = {
                        viewModel.setEvent(MyPageContract.MyPageEvent.OnClickLogOut)
                    }, text = "로그아웃")
                    Spacer(modifier = Modifier.height(20.dp))
                    MyPageBtn(onClick = {
                        viewModel.setEvent(MyPageContract.MyPageEvent.OnClickSignOut)
                    }, text = "회원탈퇴")
                }

                if (viewState.isDialogVisible) {
                    CommonDialog(
                        title = viewState.dialogMod.title,
                        onCancelButtonClick = { viewModel.setEvent(MyPageContract.MyPageEvent.OnDialogClosed) },
                        onClickPositive = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MyPageSuccessScreen(
) {
    //MyPageScreen(navigationBack = {  })
}