package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar2
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AcceptScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            StartAppBar(onStartClick = { })
        },
        containerColor = lightSkyBlue // 배경색
    ) { padding ->
        // 구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            // 상단에 위치한 Row
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = padding.calculateTopPadding() + 40.dp), // Scaffold의 패딩을 고려
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 왼쪽 이미지 (30도 회전)
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .height(19.dp)
                        .width(15.3.dp) // 이미지 크기 조정
                        .graphicsLayer(rotationZ = -120f) // 30도 회전
                )

                // 텍스트
                Text(
                    text = "당신이 누구인지 알려주세요.",
                    modifier = Modifier.padding(start = 16.dp), // 이미지와 텍스트 사이의 간격
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 40.dp) // Row 아래에 위치하도록 패딩 추가
            ) {
                val pagerState = rememberPagerState(pageCount = { 10 }) // 페이지 수를 설정
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f) // 남은 공간을 차지하게 설정
                ) { page ->
                    AcceptWho1(navController) // 각 페이지의 콘텐츠
                }

                // 페이지 인디케이터
                PageIndicator(pagerState)

                var showDialog by remember { mutableStateOf(false) }

                // 이미지 배경을 화면 중앙 오른쪽에 추가
                Box(
                    modifier = Modifier
                        .offset(x = 240.dp, y = -160.dp)
                        .clickable(onClick = {
                            showDialog = true // 클릭 시 다이얼로그 표시
                        }),
                    contentAlignment = Alignment.TopEnd // 내용 정렬을 오른쪽 아래로 설정
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_button_cloud_next_140),
                        contentDescription = "Image Background",
                        modifier = Modifier
                            .height(48.dp)
                            .width(78.dp) // 이미지 크기 조정
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState) {
    val totalPages = 10 // 표시할 페이지 수 (예: 3페이지)
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center, // 가로 방향 가운데 정렬
        verticalAlignment = Alignment.CenterVertically // 세로 방향 가운데 정렬
    ) {
        repeat(totalPages) { index ->
            val color = if (pagerState.currentPage % totalPages == index) DeepBlue else Color.Gray // 현재 페이지에 따라 색상 변경
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccept() {
    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
    AcceptScreen(navController = navController)
}