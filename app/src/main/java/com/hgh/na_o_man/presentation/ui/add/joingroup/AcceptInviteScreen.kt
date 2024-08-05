package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.runtime.Composable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.NextAppBar2
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute
import com.hgh.na_o_man.presentation.ui.add.JoinScreenRoute
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersSpace


@Composable
fun AcceptInviteScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavController,
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MembersSpace")
    var url by remember { mutableStateOf("URL을 입력해 주세요.") }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { }
            )
        },
        containerColor = lightSkyBlue // 여기를 수정
    ) { padding ->
        //구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 60.dp, y = -65.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 왼쪽 이미지 (30도 회전)
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .height(19.dp)
                        .width(15.3.dp)// 이미지 크기 조정 (필요에 따라 조정)
                        .graphicsLayer(rotationZ = -120f) // 30도 회전
                )

                // 텍스트
                Text(
                    text = "그룹 링크를 만들어주세요.",
                    modifier = Modifier.padding(start = 16.dp), // 이미지와 텍스트 사이의 간격
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // 화면 중앙 하단 이미지
            Box(
                modifier = Modifier
                    .size(width = 295.dp, height = 55.dp)
                    .background(
                        color = LightWhite.copy(alpha = 0.7f), // 투명도 0.8로 설정
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = LightWhite, shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    var textValue by remember { mutableStateOf("") }
                    var isFocused by remember { mutableStateOf(false) }

                    BasicTextField(
                        value = textValue,
                        onValueChange = { newValue -> textValue = newValue }, // 상태 업데이트
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .onFocusChanged { state ->
                                isFocused = state.isFocused
                            },
                        textStyle = TextStyle(
                            color = SteelBlue,
                            background = Color.Transparent,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        cursorBrush = SolidColor(SteelBlue),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center),
                                contentAlignment = Alignment.Center // 중앙 정렬 설정
                            ) {
                                if (textValue.isEmpty() && !isFocused) {
                                    // 입력값이 없고 포커스가 없을 때 플레이스홀더 텍스트 표시
                                    Text(
                                        text = "URL을 입력해주세요.",
                                        color = SteelBlue, // 약간의 투명도를 주어 플레이스홀더 느낌
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                innerTextField() // innerTextField 호출
                            }
                        }
                    )
                }
            }


            val context = LocalContext.current // 현재의 컨텍스트 가져오기

            LaunchedEffect(Unit) {
                viewModel.effect.collect { sideEffect ->
                    when (sideEffect) {
                        is JoinContract.JoinSideEffect._ShowToast -> {
                            Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }
                }
            }

            //이미지 배경을 화면 중앙 오른쪽에 추가
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -(40.dp), y = 70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_button_cloud_next_140),  // 이미지 리소스 ID
                    contentDescription = "Next Button",
                    modifier = Modifier
                        .clickable {
                            // URL 검증 이벤트를 뷰모델에 전달
                            viewModel.setEvent(JoinContract.JoinEvent.ValidateUrl)
                        }
                        .size(78.dp)  // 3배 크기로 설정 (140dp의 3배)
                )
                // URL 검증 상태에 따라 네비게이션 처리
                val isUrlValid by viewModel.viewState.collectAsState()

                LaunchedEffect(isUrlValid) {
                    if (viewState.isUrlValid) {
                        navController.navigate(JoinScreenRoute.CHECK.route)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview7() {
    val navController = NavHostController(context = LocalContext.current)
    AcceptInviteScreen(navController = navController)
}



