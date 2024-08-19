package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.rememberAsyncImagePainter
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
    navigationBack: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val textValue = viewState.groupName
    val extraMembersCount = viewState.members.size - viewState.members.take(3).size // 초과 인원


    if (viewState.members.isEmpty()) {
        viewModel.setEvent(JoinContract.JoinEvent.LoadGroupMembers)
        return
    }

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
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            // 그룹 확인 메시지
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 180.dp),
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

                // 폴더 이미지
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_file_227),
                        contentDescription = "Image 2",
                        modifier = Modifier.size(250.dp, 305.dp)
                            .align(Alignment.Center)
                            .graphicsLayer(
                                alpha = 0.8f,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            )
                    )

                    // 멤버-3 그 외에는 구름으로 +표시
                    viewState.members.take(3).forEachIndexed { index, member ->
                        when (viewState.members.size) {
                            1 -> 0.dp // 1명 - 중앙 정렬
                            2 -> if (index == 0) (-(30.dp)) else 30.dp // 2명 - 중앙 정렬
                            3 -> when (index) { // 3명 - 1-2-3 순서대로 겹치게
                                0 -> (-(30.dp))
                                1 -> 0.dp
                                2 -> 30.dp
                                else -> 0.dp
                            }
                            else -> 0.dp
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = 60.dp)
                                .padding(end = (index * 55).dp, bottom = 70.dp) // padding (사이 간격)
                                .graphicsLayer(translationX = (index * 80).dp.toPx()) // translation (간격)
                        ) {
                            // 프로필
                            Box(
                                modifier = Modifier
                                    .padding(end = 40.dp, top = 22.dp)
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.Transparent)
                                    .border(2.dp, LightWhite, CircleShape)
                            ) {
                                val painter = if (member.avatarUrl.isNotEmpty()) {
                                    rememberAsyncImagePainter(member.avatarUrl)
                                } else {
                                    painterResource(id = R.drawable.ic_profile_155)
                                }

                                Image(
                                    painter = painter,
                                    contentDescription = "Avatar ${member.name}",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                )
                            }
                            Text(
                                text = shortenText(member.name, 3),
                                color = SlateGray,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(end = 39.dp, top = 77.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                        }
                    }
                    // 초과 인원 수 표시
                    if (extraMembersCount > 0) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = 22.dp)
                                .padding(start = 160.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cloud_138),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(45.dp)
                                    .align(Alignment.Center)
                            )

                            Text(
                                text = "+$extraMembersCount",
                                color = DeepBlue,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 8.dp),
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
                            isPressedBack = !isPressedBack
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
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "다시 찾기",
                            color = if (isPressedBack || isFocusedBack) DeepBlue else LightWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    // "맞아요!" 버튼
                    Button(
                        onClick = {
                            viewModel.setEvent(JoinContract.JoinEvent.OnCorrect)
                            navController.navigate(JoinScreenRoute.ACCEPT.route)
                            isPressedCorrect = !isPressedCorrect
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
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "맞아요!",
                            color = if (isPressedCorrect || isFocusedCorrect) DeepBlue else LightWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                        )
                    }
                }

                // 폴더 이미지
                Box(
                    modifier = Modifier
                        .padding(top = 115.dp)
                        .size(width = 210.dp, height = 50.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(30.dp),
                            ambientColor = Color.Black.copy(alpha = 1f),
                            spotColor = Color.Black.copy(alpha = 0.8f)
                        )
                        .background(LightWhite.copy(alpha = 0.7f), shape = RoundedCornerShape(1.dp))
                        .border(1.dp, LightWhite, shape = RoundedCornerShape(1.dp))
                )
                {
                    Text(
                        text = textValue,
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

// Float으로 변환
@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return this.value * density
}

fun shortenText(text: String, maxChars: Int): String {
    return if (text.length > maxChars) {
        "${text.take(maxChars)}.."
    } else {
        text  // 텍스트 짧으면 그대로
    }
}