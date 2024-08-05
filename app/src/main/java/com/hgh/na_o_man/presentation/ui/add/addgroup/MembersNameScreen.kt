package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightNavy
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute

@Composable
fun MembersNameScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "members_name_screen")

    var nameText by remember { mutableStateOf("이름") }
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navController.navigateUp()// 뒤로가기 동작 처리
                }
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
                    .offset(y = 120.dp),
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
                    .offset(y = -(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "사진을 공유할 사람들의 이름을 추가해주세요.",
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

                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 10.dp)
                        .offset(y = 225.dp), // 위아래 여백 조정
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 첫 번째 이미지
                    Box(
                        modifier = Modifier.size(220.dp) // 큰 이미지의 크기 설정
                    ) {
                        // 큰 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share_folder_144), // 첫 번째 이미지 리소스
                            contentDescription = "Upper Image",
                            modifier = Modifier.fillMaxSize() // 큰 이미지가 Box를 채우도록 설정
                        )

                        // 작은 이미지 1
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .offset(y = 10.dp) // 왼쪽 상단에 배치
                                .padding(8.dp) // 여백 추가
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 첫 번째 작은 이미지 리소스
                                contentDescription = "Small Image 1",
                                modifier = Modifier.size(60.dp),
                                colorFilter = ColorFilter.tint(LightWhite.copy(alpha = 0.4f))// 작은 이미지 크기 설정
                            )
                            Text(
                                text = "홍길동",
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 20.dp), // 텍스트를 이미지 아래에 배치
                                color = LightNavy
                            )
                        }

                        // 작은 이미지 2
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = 10.dp) // 중앙 상단에 배치
                                .padding(8.dp) // 여백 추가
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 두 번째 작은 이미지 리소스
                                contentDescription = "Small Image 2",
                                modifier = Modifier.size(60.dp),
                                colorFilter = ColorFilter.tint(LightWhite.copy(alpha = 0.4f))// 작은 이미지 크기 설정
                            )
                            Text(
                                text = "홍길은",
                                fontWeight = FontWeight.SemiBold,
                                color = LightNavy,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 20.dp) // 텍스트를 이미지 아래에 배치
                            )
                        }

                        // 작은 이미지 3
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .offset(y = 10.dp) // 오른쪽 상단에 배치
                                .padding(8.dp) // 여백 추가
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 세 번째 작은 이미지 리소스
                                contentDescription = "Small Image 3",
                                modifier = Modifier.size(60.dp),
                                colorFilter = ColorFilter.tint(LightWhite.copy(alpha = 0.4f))// 작은 이미지 크기 설정
                            )
                            Text(
                                text = "홍길금",
                                fontWeight = FontWeight.SemiBold,
                                color = LightNavy,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 20.dp) // 텍스트를 이미지 아래에 배치
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp)) // 이미지 간의 간격

// 두 번째 이미지
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(225.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .clickable {
                                if (!isEditing) {
                                    isEditing = true
                                    nameText = "" // 클릭 시 기본 텍스트 지우기
                                }
                            }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 두 번째 이미지 리소스
                            contentDescription = "Lower Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds // 비율 무시하고 채우기
                        )

                        // 텍스트 입력 필드
                        BasicTextField(
                            value = nameText,
                            onValueChange = { newText ->
                                nameText = newText
                            },
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .offset(y = 10.dp)
                                .padding(horizontal = 8.dp), // 여백 추가
                            textStyle = TextStyle(color = SteelBlue) // 텍스트 색상
                        )

                        // 다음 화면 이동 (클릭 가능)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {

                                }
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31), // 두 번째 이미지 리소스
                            contentDescription = "Lower Image",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .height(24.dp)
                                .width(26.dp)// 이미지 크기 조정
                                .offset(x = -(10.dp), y = 0.dp),
                            contentScale = ContentScale.FillBounds // 비율 무시하고 채우기
                        )
                    }

                    val context = LocalContext.current // 현재 컨텍스트 가져오기
                    var showDialog by remember { mutableStateOf(false) }
                    var selectedAnswer by remember { mutableStateOf("") }

                    Box(
                        modifier = Modifier.offset(x = -(55.dp), y = 30.dp)
                    ) {
                        // 사이드 이펙트 수집
                        LaunchedEffect(Unit) {
                            viewModel.effect.collect { effect ->
                                when (effect) {
                                    is AddContract.AddSideEffect.NavigateToNextScreen -> {
                                        // 지정된 화면으로 네비게이션
                                        navController.navigate(AddScreenRoute.ADJECTIVE.route)
                                    }

                                    is AddContract.AddSideEffect.ShowToast -> {
                                        // 토스트 메시지 출력
                                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    // 다른 사이드 이펙트가 있다면 추가
                                    else -> {}
                                }
                            }
                        }

                        var isNextEnabled by remember { mutableStateOf(false) } // 다음 버튼 활성화 상태

// 그룹 확인 다이얼로그
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("그룹 확인") },
                                text = { Text("더 이상\n추가할 이름이 없으신가요?") },
                                confirmButton = {
                                    Button(onClick = {
                                        selectedAnswer = "네"
                                        showDialog = false // 다이얼로그 닫기
                                        isNextEnabled = true // 다음 버튼 활성화
                                    }) {
                                        Text("네")
                                    }
                                },
                                dismissButton = {
                                    Button(onClick = {
                                        selectedAnswer = "아니오"
                                        showDialog = false
                                    }) {
                                        Text("아니오")
                                    }
                                }
                            )
                        }

                        NextAppBar1(
                            onNextClick = {
                                if (isNextEnabled) {
                                    navController.navigate(AddScreenRoute.ADJECTIVE.route)
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
fun Preview2() {
    // NavController 생성
    val navController = NavHostController(context = LocalContext.current)
    MembersNameScreen(navController = navController)
}





