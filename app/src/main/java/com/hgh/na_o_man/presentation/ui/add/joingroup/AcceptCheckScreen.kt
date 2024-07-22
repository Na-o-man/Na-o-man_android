package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
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
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.Mustard
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel


@Composable
fun AcceptCheckScreen(
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","MembersSpace")
    var textValue by remember { mutableStateOf("제주도 2024") }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Scaffold(
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
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 180.dp),
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
                            text = "이 그룹이 맞으신가요?",
                            modifier = Modifier.padding(start = 16.dp), // 이미지와 텍스트 사이의 간격
                            color = LightWhite,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    var isCorrect by remember { mutableStateOf(false) }
                    var isButtonPressed by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // 이미지 1
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_folder_head_122),
                            contentDescription = "Image 1",
                            modifier = Modifier.height(260.dp).width(300.dp).padding(bottom = 200.dp, end = 142.dp)
                        )

                        // 이미지 2
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_folder_body_293),
                            contentDescription = "Image 2",
                            modifier = Modifier.height(330.dp).width(270.dp).padding(top = 9.dp)
//                                .graphicsLayer(translationY = 20f) // 살짝 겹치게
                        )

                        // 원 1
                        Box(
                            modifier = Modifier
                                .padding(end = 100.dp, bottom = 80.dp)
                                .align(Alignment.Center),
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                                contentDescription = "Image 2",
                                modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp),
                                colorFilter = ColorFilter.tint(DeepBlue)
                            )
                            // 원 아래 텍스트
                            Text(
                                text = "홍길금",
                                color = SlateGray, // 텍스트 색상
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 80.dp), // 원과의 간격
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // 원 2
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = 100.dp, bottom = 80.dp)
                                .graphicsLayer(translationX = 150f) // 살짝 겹치게
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                                contentDescription = "Image 2",
                                modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp),
                                colorFilter = ColorFilter.tint(Mustard)
                                )
                            // 원 아래 텍스트
                            Text(
                                text = "홍길은",
                                color = SlateGray, // 텍스트 색상
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 80.dp), // 원과의 간격
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                        // 원 3
                        Box(
                            modifier = Modifier
                                .padding(end = 100.dp, bottom = 80.dp)
                                .align(Alignment.Center)
                                .graphicsLayer(translationX = 300f) // 살짝 겹치게
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                                contentDescription = "Image 2",
                                modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp)
//                                .graphicsLayer(translationY = 20f) // 살짝 겹치게
                            )
                            // 원 아래 텍스트
                            Text(
                                text = "홍길동",
                                color = SlateGray, // 텍스트 색상
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 80.dp), // 원과의 간격
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    // 버튼
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 300.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { isCorrect = false },
                            modifier = Modifier
                                .padding(start = 85.dp, top = 20.dp)
                                .height(45.dp)
                                .width(105.dp)
                                .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(23.dp)) // 테두리 색상 LightWhite
                                .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(23.dp)) // 테두리 색상 LightWhite
                                .background(
                                    color = LightWhite.copy(alpha = 0.3f), // 투명도 0.3로 설정
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(LightWhite.copy(alpha = 0.3f) // 배경색 LightWhite의 30%
                            )
                        ) {
                            Text(
                                text = "다시 찾기",
                                color = if (isButtonPressed) DeepBlue else LightWhite,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = { isCorrect = true },
                            modifier = Modifier
                                .padding(end = 85.dp, top = 20.dp)
                                .height(45.dp)
                                .width(105.dp)
                                .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(23.dp)) // 테두리 색상 LightWhite
                                .background(
                                    color = LightWhite.copy(alpha = 0.3f), // 투명도 0.3로 설정
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(LightWhite.copy(alpha = 0.3f) // 배경색 LightWhite의 30%
                            )
                        ) {
                            Text(
                                text = "맞아요!",
                                color = if (isButtonPressed) DeepBlue else LightWhite,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // 화면 중앙 하단 이미지
                    Box(
                        modifier = Modifier
                            .padding(top = 90.dp)
                            .size(width = 233.dp, height = 45.1.dp)
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
                                .padding(start = 60.dp)
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
                                    color = DeepBlue,
                                    background = Color.Transparent,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                ),
                                cursorBrush = SolidColor(DeepBlue),
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
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview7() {
    AcceptCheckScreen()
}

