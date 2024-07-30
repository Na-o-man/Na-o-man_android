package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.Mustard
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel


@Composable
fun AcceptWhoScreen1(
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","AcceptWhoScreen1")
    var textValue by remember { mutableStateOf("제주도 2024") }

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
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 180.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 왼쪽 이미지 (30도 회전)
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                            contentDescription = "Left Image",
                            modifier = Modifier
                                .height(19.dp)
                                .width(15.3.dp)// 이미지 크기 조정 (필요에 따라 조정)
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

////////////who1
// 첫 번째 이미지
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                        contentDescription = null,
                        modifier = Modifier
                            .height(340.dp)
                            .width(270.dp)
                            .padding(top = 280.dp, start = 20.dp)
                            .align(Alignment.TopCenter) // 위치 조정
                            .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
                        contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                    )

                    // 두 번째 이미지
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                        contentDescription = null,
                        modifier = Modifier
                            .height(370.dp)
                            .width(265.dp)
                            .padding(top = 348.dp, start = 20.dp)
                            .align(Alignment.TopCenter) // 위치 조정
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                    )

                    // 원 1
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                            contentDescription = "Image 2",
                            modifier = Modifier
                                .height(298.dp)
                                .width(218.dp)
                                .padding(end = 40.dp, bottom = 200.dp),
                            colorFilter = ColorFilter.tint(DeepBlue)
                        )
                        // 원 아래 텍스트
                        Text(
                            text = "홍길금",
                            color = SlateGray, // 텍스트 색상
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 160.dp, bottom = 230.dp), // 원과의 간격
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

////////////who2

                    Box(
                        modifier = Modifier
                            .size(500.dp, 500.dp), // 원하는 크기로 박스 크기 설정
                        contentAlignment = Alignment.Center
                    ) {
                        // 첫 번째 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .height(80.dp)
                                .width(270.dp)
                                .padding(top = 20.dp, start = 20.dp)
                                .align(Alignment.Center) // 위치 조정
                                .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
                            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                        )

                        // 두 번째 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .height(140.dp)
                                .width(265.dp)
                                .padding(top = 118.dp, start = 20.dp)
                                .align(Alignment.Center) // 위치 조정
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                        )
                    }

                    // 프로필
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        // 원 2
                        Box(
                            modifier = Modifier
                                .padding(start = 30.dp, top = 30.dp),
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                                contentDescription = "Image 2",
                                modifier = Modifier
                                    .height(118.dp)
                                    .width(118.dp)
                                    .padding(top = 20.dp),
                                colorFilter = ColorFilter.tint(Mustard)
                            )
                            // 원 아래 텍스트
                            Text(
                                text = "홍길은",
                                color = SlateGray, // 텍스트 색상
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(start = 130.dp, bottom = 10.dp), // 원과의 간격
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

////////////Who3

                        // 첫 번째 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .height(320.dp)
                                .width(270.dp)
                                .padding(bottom = 260.dp, start = 20.dp)
                                .align(Alignment.BottomCenter) // 위치 조정
                                .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
                            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                        )

                        // 두 번째 이미지
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .height(252.dp)
                                .width(265.dp)
                                .padding(bottom = 230.dp, start = 20.dp)
                                .align(Alignment.BottomCenter) // 위치 조정
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
                        )

                        // 원 3
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(top = 340.dp, end = 50.dp)
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                                contentDescription = "Image 2",
                                modifier = Modifier
                                    .height(138.dp)
                                    .width(178.dp)
                                    .padding(bottom = 40.dp),
                            )
                            // 원 아래 텍스트
                            Text(
                                text = "홍길동",
                                color = SlateGray, // 텍스트 색상
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(start = 158.dp, bottom = 60.dp), // 원과의 간격
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // 이미지 배경을 화면 중앙 오른쪽에 추가
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 120.dp, end = 15.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_button_cloud_138),
                            contentDescription = "Image Background",
                            modifier = Modifier
                                .height(48.dp)
                                .width(78.dp)// 이미지 크기 조정
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview9() {
    AcceptWhoScreen1()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AcceptScreen() {
    val pagerState = rememberPagerState(pageCount = {3}) // 페이지 수를 설정

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f) // 남은 공간을 차지하게 설정
        ) { page ->
            when (page) {
                0 -> AcceptWhoScreen1()
                1 -> AcceptWhoScreen2()
                2 -> AcceptWhoScreen3()
            }
        }

        // 페이지 인디케이터
        PageIndicator(pagerState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .padding(16.dp)
    ) {
        repeat(3) { index ->
            val color = if (pagerState.currentPage == index) Color.Blue else Color.Gray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

@Composable
fun IndicatorDot(index: Int, currentPage: Int) {
    val color = if (index == currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    Box(
        modifier = Modifier
            .size(10.dp)
            .padding(4.dp)
            .background(LightWhite, shape = CircleShape)
    )
}
