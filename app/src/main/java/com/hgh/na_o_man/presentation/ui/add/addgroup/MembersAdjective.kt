package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
fun MembersAdjective(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "MembersAdjective")

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
            // 화면 중앙 이미지
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 140.dp),
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
                    .offset(y = 180.dp),
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
                                end = Offset(size.width + 50f, y),
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
                                        .offset(y = 1.dp)
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
                                            containerColor = if (selectedButtons[index]) SteelBlue else LightWhite.copy(
                                                alpha = 0.3f
                                            ) // 버튼 색상 LightWhite 30% // 선택된 경우 색상 변경
                                        ),
                                        border = BorderStroke(
                                            1.dp,
                                            LightWhite
                                        ) // 테두리 색상 LightWhite 100%
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

                Column {
                    // 사용자 입력을 위한 TextField 추가
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = {
                            Text(
                                text = "직접 입력", // 플레이스홀더 텍스트
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = LightWhite.copy(alpha = 0.6f) // 플레이스홀더 색상
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp, bottom = 10.dp)
                            .border(
                                BorderStroke(1.dp, LightWhite),
                                shape = RoundedCornerShape(50.dp)
                            ), // 테두리 설정
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightWhite.copy(alpha = 0.3f), // 포커스 시 배경색
                            unfocusedContainerColor = LightWhite.copy(alpha = 0.3f), // 포커스 해제 시 배경색
                            focusedIndicatorColor = Color.Transparent, // 포커스 시 밑줄 색상
                            unfocusedIndicatorColor = Color.Transparent // 포커스 해제 시 밑줄 색상
                        ),
                        shape = RoundedCornerShape(50.dp), // 배경색 설정
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done // Enter 키를 '완료'로 설정
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Enter 키 눌렀을 때의 동작
                                buttonLabels.forEachIndexed { index, label ->
                                    if (label == inputText) {
                                        selectedButtons[index] = !selectedButtons[index] // 버튼 상태 토글
                                    }
                                }
                                inputText = "" // 입력창 초기화
                            }
                        )
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    // 입력한 텍스트와 버튼 레이블이 같으면 선택 상태 변경
                    Box(
                        modifier = Modifier
                            .padding(start = 270.dp)
                            .size(48.dp) // 이미지 크기 조절
                            .clickable {
                                val index = buttonLabels.indexOf(inputText)
                                if (index != -1) {
                                    selectedButtons[index] =
                                        !selectedButtons[index] // 입력한 버튼을 선택 상태로 변경
                                }
                            },
                        contentAlignment = Alignment.CenterEnd // 이미지 중앙 정렬
                    ) {
                        val context = LocalContext.current // 현재 컨텍스트 가져오기

                        // 버튼 수 초기화
                        fun initializeButtons(buttonCount: Int) {
                            selectedButtons.clear()
                            repeat(buttonCount) { selectedButtons.add(false) }
                        }


                        // 사이드 이펙트 수집
                        LaunchedEffect(Unit) {
                            viewModel.effect.collect { effect ->
                                when (effect) {
                                    is AddContract.AddSideEffect.NavigateToNextScreen -> {
                                        // 지정된 화면으로 네비게이션
                                        navController.navigate(AddScreenRoute.SPACEINPUT.route)
                                    }

                                    is AddContract.AddSideEffect.ShowToast -> {
                                        // 토스트 메시지 출력
                                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    // 다른 사이드 이펙트가 있다면 추가
                                }
                            }
                        }

                        NextAppBar1(
                            onNextClick = {
                                // 선택된 버튼이 하나 이상일 경우 처리
                                viewModel.onNextButtonClick(selectedButtons)
                            }
                        )
                    }
                }

//                        Spacer(modifier = Modifier.height(16.dp),)

//                        Text(text = "Selected Buttons: ${selectedButtons.mapIndexed { index, isSelected -> if (isSelected) index + 1 else null }.filterNotNull()}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    // NavController 생성
    val navController = NavHostController(context = LocalContext.current) 
    MembersAdjective(navController = navController)
}



