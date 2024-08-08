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
    navController: NavHostController = rememberNavController(),
    joinName: String // 생성된 그룹 이름을 인자로 받음
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current
    var textValue by remember { mutableStateOf(joinName) } // 기본 텍스트 값으로 그룹 이름 사용
    var isButtonPressed by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = lightSkyBlue // 배경색 설정
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            // 그룹 확인 텍스트 및 이미지
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 160.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                        contentDescription = "Left Image",
                        modifier = Modifier
                            .height(19.dp)
                            .width(15.3.dp)
                            .graphicsLayer(rotationZ = -120f)
                    )

                    Text(
                        text = "이 그룹이 맞으신가요?",
                        modifier = Modifier.offset(x = 15.dp),
                        color = LightWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // 이미지들
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_folder_head_122),
                        contentDescription = "Image 1",
                        modifier = Modifier.height(90.dp).width(133.dp)
                            .offset(x = -(51.dp), y = -(93.dp))
                    )

                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_folder_body_289),
                        contentDescription = "Image 2",
                        modifier = Modifier.height(280.dp).width(240.dp)
                    )

                    // 원 1
                    Box(
                        modifier = Modifier
                            .padding(end = 100.dp, bottom = 80.dp)
                            .align(Alignment.Center)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Image 2",
                            modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp),
                            colorFilter = ColorFilter.tint(DeepBlue)
                        )
                        Text(
                            text = "홍길금",
                            color = SlateGray,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 80.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // 원 2
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = 100.dp, bottom = 80.dp)
                            .graphicsLayer(translationX = 150f)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Image 2",
                            modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp),
                            colorFilter = ColorFilter.tint(Mustard)
                        )
                        Text(
                            text = "홍길은",
                            color = SlateGray,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 80.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    // 원 3
                    Box(
                        modifier = Modifier
                            .padding(end = 100.dp, bottom = 80.dp)
                            .align(Alignment.Center)
                            .graphicsLayer(translationX = 300f)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Image 2",
                            modifier = Modifier.height(80.dp).width(80.dp).padding(top = 18.dp)
                        )
                        Text(
                            text = "홍길동",
                            color = SlateGray,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 80.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // 버튼들
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 300.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            viewModel.setEvent(JoinContract.JoinEvent.onFind)
                            Toast.makeText(context, "다시 찾는 중입니다.", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(start = 70.dp, top = 20.dp)
                            .height(45.dp)
                            .width(100.dp)
                            .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(23.dp))
                            .background(LightWhite.copy(alpha = 0.3f), shape = RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(LightWhite.copy(alpha = 0.3f))
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
                            viewModel.setEvent(JoinContract.JoinEvent.onCorrect)
                        },
                        modifier = Modifier
                            .padding(end = 70.dp, top = 20.dp)
                            .height(45.dp)
                            .width(100.dp)
                            .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(23.dp))
                            .background(LightWhite.copy(alpha = 0.3f), shape = RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(LightWhite.copy(alpha = 0.3f))
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
                        .background(LightWhite.copy(alpha = 0.7f), shape = RoundedCornerShape(20.dp))
                        .border(1.dp, LightWhite, shape = RoundedCornerShape(20.dp))
                ) {
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
}

@Preview(showBackground = true)
@Composable
fun PreviewAcceptCheckScreen() {
    val navController = rememberNavController()
    AcceptCheckScreen(navController = navController, joinName = "제주도 2024")
}



