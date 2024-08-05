package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.hardware.lights.Light
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute


@Composable
fun MembersSpace(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavController,
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    Log.d("리컴포저블", "members_space")
    var textValue by remember { mutableStateOf("공간을 입력해 주세요.") }
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
            modifier = Modifier.fillMaxSize().padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            // 화면 중앙 이미지
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = -(120.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Center Image"
                )
            }

            // 텍스트
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = -(85.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "어디에서 찍은 사진인가요?",
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2 + 10.dp.toPx()
                            drawLine(
                                color = LightWhite,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
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
                                    text = "공간을 입력해주세요.",
                                    color = SteelBlue, // 약간의 투명도를 주어 플레이스홀더 느낌
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            innerTextField() // innerTextField 호출
                        }
                    }
                )
                // 다음 버튼
                Box(
                    modifier = Modifier
                        .fillMaxSize() // 전체 크기로 채우기
                        .offset(y = 70.dp),
                            contentAlignment = Alignment.CenterEnd // 중앙 정렬
                ) {
                    NextAppBar1(
                        onNextClick = {
                            if (textValue.isNotEmpty()) { // 텍스트가 입력되었을 때만 다음 화면으로 넘어가는 로직
                                navController.navigate(AddScreenRoute._LOADING.route)
                            }
                            else {
                                AddContract.AddSideEffect.ShowToast("텍스트를 입력해주세요.") // 토스트 메시지 표시
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview4() {
    val navController = rememberNavController()
    MembersSpace(navController = navController)
}

