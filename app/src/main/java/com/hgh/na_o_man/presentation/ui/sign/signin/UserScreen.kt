package com.hgh.na_o_man.presentation.ui.sign.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.component.LineSymbol
import com.hgh.na_o_man.presentation.component.SignBtn
import com.hgh.na_o_man.presentation.component.UserProfile
import com.hgh.na_o_man.presentation.theme.Typography

@Composable
fun UserScreen(
    naviUploadScreen: () -> Unit,
    viewModel: SignViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {

        Image(
            painter = painterResource(R.drawable.background_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserProfile(userInfo = viewState.value.userInfo, modifier = Modifier.size(160.dp))
            Spacer(modifier = Modifier.height(16.dp))
            LineSymbol(Modifier.padding(horizontal = 60.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "사진 분류를 위해 인공지능을 학습 시킬 거에요.\n" +
                        "학습에 필요한 얼굴 사진을 추가해 주세요.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            SignBtn()

        }
    }
}

@Preview
@Composable
fun UserScreenPv() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {

        Image(
            painter = painterResource(R.drawable.background_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserProfile(userInfo = Dummy(), modifier = Modifier.size(160.dp))
            Spacer(modifier = Modifier.height(16.dp))
            LineSymbol(Modifier.padding(horizontal = 60.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "사진 분류를 위해 인공지능을 학습 시킬 거에요.\n" +
                        "학습에 필요한 얼굴 사진을 추가해 주세요.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            SignBtn()

        }
    }
}