package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.hardware.lights.Light
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.JoinScreenRoute


@Composable
fun AcceptCheckScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    joinName: String, // 생성된 그룹 이름을 인자로 받음
    navigationBack: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val textValue = viewState.groupName
    val extraMembersCount = viewState.members.size - viewState.members.take(3).size // 초과 인원 수 계산

    // 화면의 상태가 로딩 중이거나 멤버 정보가 없을 경우 로딩 표시
    if (viewState.members.isEmpty()) {
        viewModel.setEvent(JoinContract.JoinEvent.LoadGroupMembers)
        return // 아직 멤버 정보가 로드되지 않았으므로 이 화면을 그리지 않음
    }

    // Effect를 감지하여 화면 전환 처리
    LaunchedEffect(Unit) {
        viewModel.effect.collect { sideEffect ->
            when (sideEffect) {
                is JoinContract.JoinSideEffect.NavigateToCheckScreen -> {
                    navigationBack()
                }
                else -> {}
            }
        }
    }

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
                        .padding(top = 150.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                        contentDescription = "Left Image",
                        modifier = Modifier
                            .size(19.dp)
                            .graphicsLayer(rotationZ = -120f)
                    )

                    Text(
                        text = "이 그룹이 맞으신가요?",
                        modifier = Modifier.padding(start = 20.dp),
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
                        painter = painterResource(id = R.drawable.ic_file_227),
                        contentDescription = "Image 2",
                        modifier = Modifier.size(250.dp, 305.dp)
                            .align(Alignment.Center) // 중앙에 배치
                            .graphicsLayer(
                                alpha = 0.8f, // 전체 투명도 설정 (1은 불투명)
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            )
                    )

                    // 멤버들
                    viewState.members.take(3).forEachIndexed { index, member ->
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = 60.dp)
                                .padding(end = (index * 55).dp, bottom = 70.dp) // X축 padding 조정 (사이 간격 증가)
                                .graphicsLayer(translationX = (index * 80).dp.toPx()) // X축 translation 조정 (간격 증가)
                        ) {
                            // 동그란 배경을 포함하는 Box
                            Box(
                                modifier = Modifier
                                    .padding(end = 40.dp, top = 22.dp)
                                    .size(60.dp) // Box 크기를 설정
                                    .clip(CircleShape) // 동그란 형태로 자르기
                                    .background(Color.Transparent) // 배경색을 투명하게 설정
                                    .border(2.dp, LightWhite, CircleShape)
                            ) {
                                // 만약 avatarUrl이 null이거나 유효하지 않다면 기본 이미지를 사용
                                val painter = if (member.avatarUrl.isNotEmpty()) {
                                    rememberAsyncImagePainter(member.avatarUrl) // 사용자 정의 이미지
                                } else {
                                    painterResource(id = R.drawable.ic_profile_155) // 기본 이미지
                                }

                                Image(
                                    painter = painter,
                                    contentDescription = "Avatar ${member.name}",
                                    modifier = Modifier
                                        .fillMaxSize() // Box 전체를 채우도록 설정
                                        .clip(CircleShape) // 이미지를 동그랗게 자르기
                                )
                            }
                            Text(
                                text = shortenText(member.name, 3), // 3글자까지만 보이도록 설정
                                color = SlateGray,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(end = 39.dp, top = 77.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1, // 한 줄로 제한
                                overflow = TextOverflow.Ellipsis // 초과될 경우 말줄임표 추가
                            )

                        }
                    }
                    // 초과 인원 수 표시
                    if (extraMembersCount > 0) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = 22.dp) // 위쪽 패딩 조정
                                .padding(start = 160.dp) // 왼쪽 패딩 조정
                        ) {
                            // 배경 이미지
                            Image(
                                painter = painterResource(id = R.drawable.ic_cloud_138),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(45.dp) // 이미지 크기 조정
                                    .align(Alignment.Center) // 이미지 정렬
                            )

                            // 초과 인원 수 텍스트
                            Text(
                                text = "+$extraMembersCount", // 초과 인원 수 표시
                                color = DeepBlue,
                                modifier = Modifier
                                    .align(Alignment.Center) // 텍스트를 이미지 중앙에 배치
                                    .padding(horizontal = 8.dp), // 텍스트와 이미지 간의 간격 조정
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // 각 버튼의 상태 변수
                var isPressedBack by remember { mutableStateOf(false) }
                var isFocusedBack by remember { mutableStateOf(false) }

                var isPressedCorrect by remember { mutableStateOf(false) }
                var isFocusedCorrect by remember { mutableStateOf(false) }

                val focusRequesterBack = remember { FocusRequester() }
                val focusRequesterCorrect = remember { FocusRequester() }

                Row(
                    modifier = Modifier
                        .width(240.dp)
                        .padding(top = 300.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // "다시 찾기" 버튼
                    Button(
                        onClick = {
                            navigationBack()
                            isPressedBack = !isPressedBack // Toggle button state
                        },
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .size(84.dp, 40.dp)
                            .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(50.dp))
                            .background(
                                color = LightWhite.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .focusRequester(focusRequesterBack)
                            .onFocusChanged { focusState ->
                                isFocusedBack = focusState.isFocused
                            },
                        contentPadding = PaddingValues(0.dp), // Remove default padding
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,  // Handle with background modifier
                            contentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "다시 찾기",
                            color = if (isPressedBack || isFocusedBack) DeepBlue else LightWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center) // Center text in button
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    // "맞아요!" 버튼
                    Button(
                        onClick = {
                            viewModel.setEvent(JoinContract.JoinEvent.onCorrect)
                            navController.navigate(JoinScreenRoute.ACCEPT.route)
                            isPressedCorrect = !isPressedCorrect // Toggle button state
                        },
                        modifier = Modifier
                            .padding(end = 32.dp)
                            .size(84.dp, 40.dp)
                            .border(BorderStroke(1.dp, LightWhite), shape = RoundedCornerShape(50.dp))
                            .background(
                                color = LightWhite.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .focusRequester(focusRequesterCorrect)
                            .onFocusChanged { focusState ->
                                isFocusedCorrect = focusState.isFocused
                            },
                        contentPadding = PaddingValues(0.dp), // Remove default padding
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,  // Handle with background modifier
                            contentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "맞아요!",
                            color = if (isPressedCorrect || isFocusedCorrect) DeepBlue else LightWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center) // Center text in button
                        )
                    }
                }


                // 화면 중앙 하단 이미지
                Box(
                    modifier = Modifier
                        .padding(top = 115.dp)
                        .size(width = 210.dp, height = 50.dp)
                        .shadow(
                            elevation = 8.dp, // 그림자의 높이 (값을 조정하여 그림자 강도를 변경)
                            shape = RoundedCornerShape(30.dp),
                            ambientColor = Color.Black.copy(alpha = 1f), // 그림자 색상 및 투명도
                            spotColor = Color.Black.copy(alpha = 0.8f) // 빛에 의해 생긴 그림자의 색상 및 투명도
                        )
                        .background(LightWhite.copy(alpha = 0.7f), shape = RoundedCornerShape(1.dp)) // 덜 둥근 모서리 배경
                        .border(1.dp, LightWhite, shape = RoundedCornerShape(1.dp)) // 덜 둥근 모서리 테두리
                )
                {
                    Text(
                        text = textValue, // 그룹 이름을 여기에 표시
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 15.dp)
                            .align(Alignment.Center),
                        style = TextStyle(
                            color = DeepBlue,
                            background = Color.Transparent,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

// Dp를 Float으로 변환하는 유틸리티 함수
@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return this.value * density
}

fun shortenText(text: String, maxChars: Int): String {
    return if (text.length > maxChars) {
        "${text.take(maxChars)}.."  // 텍스트를 maxChars만큼 자르고 '...' 추가
    } else {
        text  // 텍스트가 짧으면 그대로 반환
    }
}

//@Composable
//fun SomeScreen(
//    navController: NavHostController = rememberNavController(),
//    addViewModel: AddViewModel = hiltViewModel()
//) {
//    val groupName by remember { mutableStateOf(addViewModel.getGroupName()) }
//
//    AcceptCheckScreen(
//        navController = navController,
//        joinName = groupName
//    )
//}
