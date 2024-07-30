package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue


@Composable
fun MembersSpace(
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","MembersSpace")
    var textValue by remember { mutableStateOf("공간을 입력해 주세요.") }

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
                        onStartClick = { }
                    )
                    
                },
                containerColor = Color.Black // 여기를 수정
            ) { padding ->
                //구름 배경 Box
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // 화면 중앙 이미지
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 155.dp),
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
                            .padding(bottom = 80.dp),
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
                            .padding(top = 90.dp)
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
                        // 이미지 위에 텍스트
                        var isFocused by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .padding(start = 79.dp)
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        ) {
                            BasicTextField(
                                value = textValue,
                                onValueChange = { textValue = it },
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
                                            .align(Alignment.Center)
                                    ) {
                                        innerTextField()
                                    }
                                }
                            )
                        }
                    }

                    // 이미지 배경을 화면 중앙 오른쪽에 추가
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 220.dp, end = 50.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_button_next_38),
                            contentDescription = "Image Background",
                            modifier = Modifier
                                .size(38.dp) // 이미지 크기 조정
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview4() {
    MembersSpace()
}


