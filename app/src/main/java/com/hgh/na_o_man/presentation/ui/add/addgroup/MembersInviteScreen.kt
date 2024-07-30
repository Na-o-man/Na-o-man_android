package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersInviteScreen(
    navController: NavController, // NavController 전달
    viewModel: AddViewModel = hiltViewModel(),
    ) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","MembersInviteScreen")
    var textValue by remember { mutableStateOf("인원 수를 입력해주세요.") }

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
//                    // 왼쪽 상단 이미지
//                    Image(
//                        imageVector = ImageVector.vectorResource(R.drawable.ic_button_back_arrow_38),
//                        contentDescription = "Left Top Image",
//                        modifier = Modifier
//                            .align(Alignment.TopStart)
//                            .padding(start = 30.dp, top = 20.dp)
//                    )

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
                            text = "몇 명이서 사진을 공유할 건가요?",
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
                            color = LightWhite
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
                                    fontWeight = FontWeight.Bold,
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

                    // 이미지 배경 추가
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(top = 220.dp, end = 50.dp)
                            .clickable {
                                // MembersSpace로 이동
                                navController.navigate("membersNameScreen")
                            }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_next_38),
                            contentDescription = "Image Background",
                            modifier = Modifier.size(38.dp) // 이미지 크기 조정
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview1() {
    // NavController 생성
    val navController = rememberNavController()

    // AddViewModel의 더미 인스턴스 생성
    val dummyViewModel = object : AddViewModel() {
        // 필요한 프로퍼티나 메소드 오버라이드
    }

    // MembersInviteScreen에 navController와 dummyViewModel 전달
    MembersInviteScreen(viewModel = dummyViewModel, navController = navController)
}

