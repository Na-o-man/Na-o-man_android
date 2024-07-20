package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.samplecompose.R

@Composable
fun MembersNameScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavController //NavController 추가
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MembersNameScreen")
    var nameText by remember { mutableStateOf("이름") }
    val isEditing by remember { mutableStateOf(false) }

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
//                    // 왼쪽 상단 이미지 (뒤로가기 버튼)
//                    Image(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back_arrow_38),
//                        contentDescription = "Left Top Image",
//                        modifier = Modifier
//                            .align(Alignment.TopStart)
//                            .padding(start = 30.dp, top = 20.dp)
//                            .clickable {
//                                // 뒤로가기 클릭 시 MembersInviteScreen으로 이동
//                                navController.navigate("MembersInviteScreen") {
//                                    // 이전 화면을 스택에서 제거하고 새 화면으로 이동
//                                    popUpTo("MembersNameScreen") { inclusive = true }
//                                }
//                            }
//                    )


                    // 화면 중앙 이미지
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 160.dp),
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
                            .align(Alignment.TopCenter)
                            .padding(top = 205.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "각 멤버의 이름을 입력해주세요.",
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

///////////////////////////  1번 프로필

                    // 진행 바
                    Box(
                        modifier = Modifier
                            .padding(start = 120.dp, end = 50.dp, bottom = 150.dp) // 프로필 이미지 밑에 위치
                            .fillMaxWidth()
                            .height(13.dp)
                            .background(
                                color = LightWhite.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.18f) // 진행률 1/6
                                .fillMaxHeight()
                                .background(
                                    color = SteelBlue,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )

                        Text(
                            text = "1/6",
                            color = LightWhite,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                        )
                    }

                    // 화면 중앙 이미지
                    Row(
                        modifier = Modifier
                            .padding(start = 40.dp, bottom = 200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 왼쪽 동그란 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(94.dp)
                        )

                        // 오른쪽 이름 텍스트와 진행 바
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            // 도형
                            Box(
                                modifier = Modifier
                                    .size(width = 251.dp, height = 73.dp)
                                    .padding(end = 50.dp, bottom = 35.dp)
                                    .background(
                                        color = LightWhite.copy(alpha = 0.5f), // 투명도 0.5로 설정
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = LightWhite, shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                // 이름 텍스트
                                BasicTextField(
                                    value = nameText,
                                    onValueChange = { nameText = it },
                                    modifier = Modifier
                                        .padding(start = 14.dp, top = 10.dp)
                                        .fillMaxSize(),
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SteelBlue
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(color = Color.Transparent)
                                                .align(Alignment.Center)
                                        ) {
                                            innerTextField()
                                        }

                                    }
                                )
                            }
                        }
                    }

///////////////2번 프로필

                    // 진행 바
                    Box(
                        modifier = Modifier
                            .padding(start = 120.dp, end = 50.dp, top = 150.dp) // 프로필 이미지 밑에 위치
                            .fillMaxWidth()
                            .height(13.dp)
                            .background(
                                color = LightWhite.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.18f) // 진행률 1/6
                                .fillMaxHeight()
                                .background(
                                    color = SteelBlue,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )

                        Text(
                            text = "1/6",
                            color = LightWhite,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                        )
                    }

                    // 화면 중앙 이미지
                    Row(
                        modifier = Modifier
                            .padding(start = 40.dp, top = 100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 왼쪽 동그란 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(94.dp)
                        )

                        // 오른쪽 이름 텍스트와 진행 바
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            // 도형
                            Box(
                                modifier = Modifier
                                    .size(width = 251.dp, height = 73.dp)
                                    .padding(end = 50.dp, bottom = 35.dp)
                                    .background(
                                        color = LightWhite.copy(alpha = 0.5f), // 투명도 0.5로 설정
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = LightWhite, shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                            // 이름 텍스트
                                BasicTextField(
                                    value = nameText,
                                    onValueChange = { nameText = it },
                                    modifier = Modifier
                                        .padding(start = 14.dp, top = 10.dp)
                                        .fillMaxSize(),
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SteelBlue
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(color = Color.Transparent)
                                                .align(Alignment.Center)
                                                ) {
                                                    innerTextField()
                                                }

                                    }
                                )
                            }
                        }
                    }

///////////////////// 3번 프로필

                    // 진행 바
                    Box(
                        modifier = Modifier
                            .padding(start = 120.dp, end = 50.dp, top = 450.dp) // 프로필 이미지 밑에 위치
                            .fillMaxWidth()
                            .height(13.dp)
                            .background(
                                color = LightWhite.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.18f) // 진행률 1/6
                                .fillMaxHeight()
                                .background(
                                    color = SteelBlue,
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )

                        Text(
                            text = "1/6",
                            color = LightWhite,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                        )
                    }

                    // 화면 중앙 이미지
                    Row(
                        modifier = Modifier
                            .padding(start = 40.dp, top = 400.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 왼쪽 동그란 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(94.dp)
                        )

                        // 오른쪽 이름 텍스트와 진행 바
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            // 도형
                            Box(
                                modifier = Modifier
                                    .size(width = 251.dp, height = 73.dp)
                                    .padding(end = 50.dp, bottom = 35.dp)
                                    .background(
                                        color = LightWhite.copy(alpha = 0.5f), // 투명도 0.5로 설정
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = LightWhite, shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                // 이름 텍스트
                                BasicTextField(
                                    value = nameText,
                                    onValueChange = { nameText = it },
                                    modifier = Modifier
                                        .padding(start = 14.dp, top = 10.dp)
                                        .fillMaxSize(),
                                    textStyle = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SteelBlue
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(color = Color.Transparent)
                                                .align(Alignment.Center)
                                        ) {
                                            innerTextField()
                                        }

                                    }
                                )
                            }
                        }
                    }

                    // 이미지 배경 (클릭 가능)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 130.dp, end = 50.dp)
                            .clickable {
                                // MembersAdjective로 이동
                                navController.navigate("membersAdjective")
                            }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_next_38),
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
fun Preview2() {
    // NavController 생성
    val navController = rememberNavController()

    // AddViewModel의 더미 인스턴스 생성
    val dummyViewModel = object : AddViewModel() {
        // 필요한 프로퍼티나 메소드 오버라이드
    }

    // MembersNameScreen에 navController와 dummyViewModel 전달
    MembersNameScreen(viewModel = dummyViewModel, navController = navController)
}


