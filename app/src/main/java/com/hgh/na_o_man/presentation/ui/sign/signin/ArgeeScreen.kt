package com.hgh.na_o_man.presentation.ui.sign.signin

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.Typography

@Composable
fun ArgeeScreen(
    naviUserScreen: () -> Unit,
    viewModel: SignViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity
    var isTermsAgreed by remember { mutableStateOf(false) }
    var isPrivacyAgreed by remember { mutableStateOf(false) }
    var isAdsAgreed by remember { mutableStateOf(false) }

    Log.d("리컴포저블", "ArgeeScreen")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SignContract.SignSideEffect.NaviUser -> {
                    naviUserScreen()
                }

                is SignContract.SignSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.background(Color(0xFFBBCFE5))
        ) {
            Image(
                painter = painterResource(R.drawable.background_2),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고 이미지",
                    modifier = Modifier
                        .width(200.dp)
                        .height(90.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                AgreeComponent(
                    isTermsAgreed = isTermsAgreed,
                    onTermsAgreeChange = { isTermsAgreed = it },
                    isPrivacyAgreed = isPrivacyAgreed,
                    onPrivacyAgreeChange = { isPrivacyAgreed = it },
                    isAdsAgreed = isAdsAgreed,
                    onAdsAgreeChange = { isAdsAgreed = it },
                    onNaviUpload = { viewModel.setEvent(SignContract.SignEvent.OnClickALlAgree) }
                )
            }
        }
    }
}

@Composable
fun AgreeComponent(
    isTermsAgreed: Boolean,
    onTermsAgreeChange: (Boolean) -> Unit,
    isPrivacyAgreed: Boolean,
    onPrivacyAgreeChange: (Boolean) -> Unit,
    isAdsAgreed: Boolean,
    onAdsAgreeChange: (Boolean) -> Unit,
    onNaviUpload: () -> Unit,
) {

    Log.d("리컴포저블", "AgreeComponent")
    Column {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_background_agree_276),
                contentDescription = "동의화면",
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .matchParentSize(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "심볼",
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "약관 동의가 필요해요.", color = Color(0xFF7B7B7B))
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(215.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_essential_37),
                        contentDescription = "필수 이미지",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "이용 약관 동의",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_check_25),
                        contentDescription = "체크 이미지",
                        tint = if (!isTermsAgreed) Color.Unspecified else Color(0xFF7C93CD),
                        modifier = Modifier.clickable {
                            onTermsAgreeChange(!isTermsAgreed)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(215.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_essential_37),
                        contentDescription = "필수 이미지",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "개인 정보 이용 동의",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_check_25),
                        contentDescription = "체크 이미지",
                        tint = if (!isPrivacyAgreed) Color.Unspecified else Color(0xFF7C93CD),
                        modifier = Modifier.clickable {
                            onPrivacyAgreeChange(!isPrivacyAgreed)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(215.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_select_37),
                        contentDescription = "필수 이미지",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "광고 약관 동의",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_check_25),
                        contentDescription = "체크 이미지",
                        tint = if (!isAdsAgreed) Color.Unspecified else Color(0xFF7C93CD),
                        modifier = Modifier.clickable {
                            onAdsAgreeChange(!isAdsAgreed)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(230.dp)
                ) {
                    Text(
                        text = "약관 전체 동의",
                        fontSize = 16.sp,
                        color = Color(0xFF4879AF),
                        maxLines = 1,
                        style = Typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_circle_30),
                        contentDescription = "체크 이미지",
                        tint = if (isTermsAgreed && isPrivacyAgreed && isAdsAgreed ) Color.Unspecified else  Color(0xFFFFFFFF) ,
                        modifier = Modifier.clickable {
                            onAdsAgreeChange(true)
                            onTermsAgreeChange(true)
                            onPrivacyAgreeChange(true)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isTermsAgreed && isPrivacyAgreed) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(230.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Surface(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .size(85.dp, 40.dp)
                                .border(
                                    2.dp, Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x00FFFFFF),
                                            Color(0xFFFFFFFF),
                                        ),
                                    ), RoundedCornerShape(20.dp)
                                )
                                .clickable {
                                    onNaviUpload()
                                },
                            shadowElevation = 2.dp,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,

                                ) {
                                Text(
                                    text = "회원가입",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF4879AF),
                                    modifier = Modifier,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )

                            }
                        }
                    }
                }
                else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.background(Color(0xFFBBCFE5))
        ) {
            Image(
                painter = painterResource(R.drawable.background_2),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고 이미지",
                    modifier = Modifier
                        .width(200.dp)
                        .height(90.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                AgreeComponent(
                    true, { }, true, {}, false, {}, {}
                )
            }
        }
    }
}