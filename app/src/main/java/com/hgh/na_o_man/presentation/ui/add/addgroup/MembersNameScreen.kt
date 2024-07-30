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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightNavy
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue

@Composable
fun MembersNameScreen(
    viewModel: AddViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "members_name_screen")
    var nameText by remember { mutableStateOf(viewState.nickname) }
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
                bottomBar = {
                    NextAppBar1(
                        modifier = Modifier.padding(bottom = 190.dp, end = 82.dp),
                        onStartClick = { viewModel.navigateUp() },
                        onEndClick = {
                            // 자신의 프로필을 추가하는 로직을 여기에 작성합니다.
                            viewModel.addSelfToGroup2() // 프로필 추가 메서드 호출
                        },
                        onNextClick = {
                            // 해당 그룹에서의 닉네임 변경 후 다음 화면으로 이동 viewModel.updateNickname(nameText)
                            viewModel.addNicknameToGroup(nameText)
                            viewModel.navigate("members_adjective")
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
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 10.dp)
                            .padding(top = 50.dp), // 위아래 여백 조정
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
                                    .align(Alignment.CenterStart).padding(top = 20.dp) // 왼쪽 상단에 배치
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
                                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp), // 텍스트를 이미지 아래에 배치
                                    color = LightNavy
                                )
                            }

                            // 작은 이미지 2
                            Box(
                                modifier = Modifier
                                    .align(Alignment.Center).padding(top = 20.dp) // 중앙 상단에 배치
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
                                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp) // 텍스트를 이미지 아래에 배치
                                )
                            }

                            // 작은 이미지 3
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd).padding(top = 20.dp) // 오른쪽 상단에 배치
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
                                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp) // 텍스트를 이미지 아래에 배치
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp)) // 이미지 간의 간격

                        var nameText by remember { mutableStateOf("이름") }
                        var isEditing by remember { mutableStateOf(false) }

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
                                    .padding(top = 10.dp, start = 10.dp)
                                    .padding(horizontal = 8.dp), // 여백 추가
                                textStyle = TextStyle(color = SteelBlue) // 텍스트 색상
                            )

                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31), // 두 번째 이미지 리소스
                                contentDescription = "Lower Image",
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .height(24.dp)
                                    .width(33.dp)// 이미지 크기 조정
                                    .align(Alignment.CenterEnd) // 이미지가 오른쪽에 위치하도록 설정
                                    .padding(end = 8.dp), // 이미지와 텍스트 간격 조정
                                contentScale = ContentScale.FillBounds // 비율 무시하고 채우기
                            )
                        }
                    }

                    // 다음 화면 이동 (클릭 가능)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 130.dp, end = 50.dp)
                            .clickable {
                                // MembersAdjective로 이동
                                viewModel.navigate("membersAdjective")
                            }
                    )
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

    val dummyViewModel = object : AddViewModel(navController) {
        // 필요한 프로퍼티나 메소드 오버라이드
        // 예: nickname = "더미닉네임"
    }
    // MembersNameScreen에 dummyViewModel 전달
    MembersNameScreen(viewModel = dummyViewModel)
}


