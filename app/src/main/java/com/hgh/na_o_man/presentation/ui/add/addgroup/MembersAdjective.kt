package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun MembersAdjective(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavController // NavController 추가
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MembersAdjective")
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
//                                // 이전 화면으로 돌아가기
//                                navController.popBackStack() // MembersAdjective로 돌아가기
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
                            text = "모임의 성격을 알려주세요.\n " +
                                    "\t\t\t\t(중복 선택 가능)",
                            modifier = Modifier
                                .drawBehind {
                                    val strokeWidth = 1.dp.toPx()
                                    val y = size.height - strokeWidth / 2 + 12.dp.toPx()
                                    drawLine(
                                        color = LightWhite,
                                        start = Offset(-50f, y),
                                        end = Offset(size.width+50f, y),
                                        strokeWidth = strokeWidth
                                    )
                                },
                            color = LightWhite,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

////////////중복 선택

                    val buttonLabels = listOf("친구", "연인", "여행", "가족", "정기 모임", "동아리", "행사", "나들이", "스냅 사진")
                    val buttonCount = 9
                    val selectedButtons = remember { mutableStateListOf<Boolean>().apply { repeat(buttonCount) { add(false) } } }
                    var inputText by remember { mutableStateOf("") } // 사용자 입력을 저장할 상태 변수

                    Column(
                        modifier = Modifier
                            .padding(start = 35.dp, end = 35.dp, bottom = 20.dp, top = 150.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // 3x3 버튼 배열
                        for (row in 0 until 3) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                for (col in 0 until 3) {
                                    val index = row * 3 + col
                                    if (index < buttonCount) { // 버튼 수를 체크하여 범위 내에서만 처리
                                        Box(
                                            modifier = Modifier
                                                .width(99.dp) // 각 버튼의 너비를 99.dp로 설정
                                                .height(42.dp) // 버튼의 높이를 42.dp로 설정
                                        ) {
                                            Button(
                                                onClick = {
                                                    selectedButtons[index] = !selectedButtons[index]
                                                },
                                                modifier = Modifier.fillMaxSize(), // Box의 크기에 맞게 버튼 크기 조절
                                                shape = RoundedCornerShape(20.dp), // 둥근 모서리 설정
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (selectedButtons[index]) SteelBlue else LightWhite.copy(alpha = 0.3f) // 버튼 색상 LightWhite 30% // 선택된 경우 색상 변경
                                                ),
                                                border = BorderStroke(1.dp, LightWhite) // 테두리 색상 LightWhite 100%
                                            ) {
                                                Text(
                                                    text = buttonLabels[index], // 버튼 텍스트 설정
                                                    fontWeight = FontWeight.SemiBold, // 글씨를 굵게 설정
                                                    fontSize = 13.sp // 글씨 크기를 14sp로 설정
                                                    )
                                            }
                                        }

                                        // 버튼 사이에 Spacer 추가 (가로 간격 조절)
                                        if (col < 2) { // 마지막 열에는 Spacer 추가하지 않음
                                            Spacer(modifier = Modifier.width(5.dp)) // 원하는 간격 설정
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp)) // 각 행 사이의 세로 간격 조절
                        }

                        // 사용자 입력을 위한 TextField 추가
                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            label = {
                                Text(
                                    text = "\t직접 입력",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                                    },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 25.dp, bottom = 10.dp)
                                .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(50.dp)), // 테두리 설정
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = LightWhite.copy(alpha = 0.3f), // 포커스 시 배경색
                                unfocusedContainerColor = LightWhite.copy(alpha = 0.3f), // 포커스 해제 시 배경색
                                unfocusedLabelColor = LightWhite, // 텍스트 색상 설정
                                focusedLabelColor = LightWhite,
                                focusedIndicatorColor = Color.Transparent, // 포커스 시 밑줄 색상
                                unfocusedIndicatorColor = Color.Transparent // 포커스 해제 시 밑줄 색상
                            ),
                            shape = RoundedCornerShape(50.dp)) // 배경색 설정

                        Spacer(modifier = Modifier.height(16.dp))

                        // 입력한 텍스트와 버튼 레이블이 같으면 선택 상태 변경
                        Box(
                            modifier = Modifier
                                .padding(start = 270.dp)
                                .size(48.dp) // 이미지 크기 조절
                                .clickable {
                                    val index = buttonLabels.indexOf(inputText)
                                    if (index != -1) {
                                        selectedButtons[index] = !selectedButtons[index] // 입력한 버튼을 선택 상태로 변경
                                    }
                                },
                            contentAlignment = Alignment.CenterEnd // 이미지 중앙 정렬
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_button_next_38), // 이미지 리소스
                                contentDescription = "선택하기 버튼", // 이미지 설명
                                modifier = Modifier.size(38.dp) // 이미지 크기 설정
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp),)

                        Text(text = "Selected Buttons: ${selectedButtons.mapIndexed { index, isSelected -> if (isSelected) index + 1 else null }.filterNotNull()}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    // NavController 생성
    val navController = rememberNavController()

    // AddViewModel의 더미 인스턴스 생성
    val dummyViewModel = object : AddViewModel() {
        // 필요한 프로퍼티나 메소드 오버라이드
    }

    // MembersNameScreen에 navController와 dummyViewModel 전달
    MembersAdjective(viewModel = dummyViewModel, navController = navController)
}

