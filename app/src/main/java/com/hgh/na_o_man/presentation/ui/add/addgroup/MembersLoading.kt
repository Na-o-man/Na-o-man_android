package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import kotlinx.coroutines.delay


@Composable
fun MembersLoading(
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","MembersSpace")
    var textValue by remember { mutableStateOf("공간을 입력해 주세요.") }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Scaffold(
                containerColor = lightSkyBlue // 여기를 수정
            ) { padding ->
                //구름 배경 Box
                Box(modifier = Modifier.fillMaxSize()) {
                    StartTopCloud()
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    // 회전 각도 상태
                    val rotationState = remember { Animatable(0f) }

                    // 로딩 애니메이션 루프
                    LaunchedEffect(Unit) {
                        while (true) {
                            rotationState.animateTo(
                                targetValue = 360f,
                                animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                            )
                            rotationState.snapTo(0f) // 애니메이션 끝나면 0도로 재설정
                        }
                    }

                    // 로딩 이미지
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                            contentDescription = "Center Image",
                            modifier = Modifier
                                .graphicsLayer(rotationZ = rotationState.value) // 회전 적용
                                .graphicsLayer(rotationZ = -120f) // 30도 회전
                                .size(60.dp)
                        )
                    }

                    // 애니메이션 상태 초기화
                    val offsetY = remember { Animatable(0f) }

                    // 애니메이션 루프
                    LaunchedEffect(Unit) {
                        while (true) {
                            offsetY.animateTo(
                                targetValue = 10f, // 위로 이동하는 거리
                                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                            )
                            offsetY.animateTo(
                                targetValue = -10f, // 아래로 이동하는 거리
                                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                            )
                            offsetY.animateTo(
                                targetValue = 0f, // 원래 위치로 복귀
                                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                            )
                        }
                    }

                    // 텍스트
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "그룹 생성 중입니다...",
                            modifier = Modifier.graphicsLayer(translationY = offsetY.value), // Y축으로 이동
                            color = LightWhite,
                            fontWeight = FontWeight.SemiBold, // 굵게 설정
                            fontSize = 16.sp // 크기를 16sp로 설정
                        )
                    }
                }
            }

            // 5초 후 MembersFolder로 이동
            LaunchedEffect(Unit) {
                viewModel.addGroup5() // 5초 후 MembersFolder로 이동
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview5() {
    // NavController 생성
    val navController = rememberNavController()

    // AddViewModel의 더미 인스턴스 생성
    val dummyViewModel = object : AddViewModel(navController) {
        // 필요한 프로퍼티나 메소드 오버라이드
    }

    // MembersLoading에 viewModel과 dummyViewModel 전달
    MembersLoading(viewModel = dummyViewModel)
}


