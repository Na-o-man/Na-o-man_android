package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.Mustard
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.theme.lightSkyBlue


@Composable
fun AcceptCheckScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MembersSpace")
    var textValue by remember { mutableStateOf("제주도 2024") }

    Scaffold(
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
                    .align(Alignment.TopCenter)
                    .offset(y = 160.dp),
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
                    modifier = Modifier.offset(x = 15.dp), // 이미지와 텍스트 사이의 간격
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
                    modifier = Modifier.height(90.dp).width(133.dp)
                        .offset(x = -(51.dp), y = -(93.dp))
                )

                // 이미지 2
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_folder_body_289),
                    contentDescription = "Image 2",
                    modifier = Modifier.height(280.dp).width(240.dp)
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

            val context = LocalContext.current

            // 버튼
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 300.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // 다시 찾기 버튼 클릭 시 Toast 메시지 표시
                        viewModel.handleEvents(JoinContract.JoinEvent.onFind) // 다시 찾기 이벤트 호출
                        Toast.makeText(context, "다시 찾는 중입니다.", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .padding(start = 70.dp, top = 20.dp)
                        .height(45.dp)
                        .width(100.dp)
                        .border(
                            BorderStroke(1.dp, LightWhite),
                            shape = RoundedCornerShape(23.dp)
                        ) // 테두리 색상 LightWhite
                        .border(
                            BorderStroke(1.dp, LightWhite),
                            shape = RoundedCornerShape(23.dp)
                        ) // 테두리 색상 LightWhite
                        .background(
                            color = LightWhite.copy(alpha = 0.3f), // 투명도 0.3로 설정
                            shape = RoundedCornerShape(20.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        LightWhite.copy(alpha = 0.3f) // 배경색 LightWhite의 30%
                    )
                ) {
                    Text(
                        text = "다시 찾기",
                        color = if (isButtonPressed) DeepBlue else LightWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Button(
                    onClick = {
                        isCorrect = true
                        // 맞아요 버튼 클릭 시 AcceptWhoScreen으로 이동
                        viewModel.handleEvents(JoinContract.JoinEvent.onCorrect)
                    },
                    modifier = Modifier
                        .padding(end = 70.dp, top = 20.dp)
                        .height(45.dp)
                        .width(100.dp)
                        .border(
                            BorderStroke(1.dp, LightWhite),
                            shape = RoundedCornerShape(23.dp)
                        ) // 테두리 색상 LightWhite
                        .background(
                            color = LightWhite.copy(alpha = 0.3f), // 투명도 0.3로 설정
                            shape = RoundedCornerShape(20.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        LightWhite.copy(alpha = 0.3f) // 배경색 LightWhite의 30%
                    )
                ) {
                    Text(
                        text = "맞아요!",
                        color = if (isButtonPressed) DeepBlue else LightWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // 화면 중앙 하단 이미지 "제주도 2024"
            Box(
                modifier = Modifier
                    .padding(top = 90.dp)
                    .size(width = 210.dp, height = 45.dp)
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
                        .padding(start = 48.dp)
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


@Preview(showBackground = true)
@Composable
fun Preview8() {
    val navController = NavHostController(context = LocalContext.current)
    AcceptCheckScreen(navController = navController)
}


