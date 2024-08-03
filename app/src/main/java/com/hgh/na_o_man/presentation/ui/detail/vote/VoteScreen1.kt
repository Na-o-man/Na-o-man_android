package com.hgh.na_o_man.presentation.ui.detail.vote

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.common.util.AndroidUtilsLight
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.PlusAppBar
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartBottomCloud
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue


@Composable
fun VoteScreen1(navController: NavHostController) {
    Log.d("리컴포저블", "Vote1Screen")

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { /* TODO: Handle start click */ }
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                PlusAppBar(
                    onPlusClick = {
                        navController.navigate("votescreen2")
                    }
                )
            }
        },
        containerColor = lightSkyBlue
    ) { padding ->
        // 구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            StartBottomCloud()
        }


        Box(
            modifier = Modifier
                .fillMaxSize(), // 전체 화면을 채우도록 설정
            contentAlignment = Alignment.TopStart // 중앙 정렬
        ) {
            // 위쪽 이미지
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_notice_yellow_box_148), // 위쪽 이미지 리소스
                contentDescription = "토글", // 적절한 설명 추가
                modifier = Modifier
                    .size(115.dp) // 원하는 크기로 설정
                    .offset(y = 40.dp)

            )
            // 텍스트 추가
            Text(
                text = "제주도", // 표시할 텍스트
                color = Color.Black, // 텍스트 색상
                modifier = Modifier
                    .offset(x = 50.dp, y = 86.dp)
                    .align(Alignment.TopStart), // 이미지 아래쪽에 위치
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize() // 전체 화면을 채우도록 설정
                .padding(bottom = 10.dp)
                .offset(y = 10.dp),
            contentAlignment = Alignment.Center // 중앙 정렬
        ) {
            // 첫 번째 이미지
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_no_group_message_299),
                contentDescription = "message", // 적절한 설명 추가
                modifier = Modifier.size(285.dp) // 원하는 크기로 설정
            )

            Text(
                text = "아직 안건이 없어요.", // 첫째 줄 텍스트
                color = Color.White, // 텍스트 색상
                textAlign = TextAlign.Center, // 텍스트 중앙 정렬
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .offset(y = -70.dp),
            )

            Text(
                text = "우측 상단의 플러스 버튼을 눌러", // 둘째 줄 텍스트
                color = Color.White, // 텍스트 색상
                textAlign = TextAlign.Center, // 텍스트 중앙 정렬
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .offset(y = -35.dp),
            )
            Spacer(modifier = Modifier.height(26.dp)) // 둘째 줄과 셋째 줄 사이의 간격
            Text(
                text = "새로운 안건을 추가해보세요.", // 셋째 줄 텍스트
                color = Color.White, // 텍스트 색상
                textAlign = TextAlign.Center, // 텍스트 중앙 정렬
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )

            // 두 번째 버튼 (안건 추가하기)
            Box(
                modifier = Modifier
                    .fillMaxSize() // 전체 화면을 채우도록 설정
                    .padding(bottom = 10.dp)
                    .offset(y = 30.dp),
                contentAlignment = Alignment.Center // 중앙 정렬
            ) {
                // 두 번째 버튼 (안건 추가하기)
                Box(
                    modifier = Modifier
                        .size(166.dp) // 같은 크기로 설정
                        .offset(y = 40.dp) // 원하는 오프셋 조정
                        .clickable(onClick = { /* 버튼 클릭 시 동작 */ }), // 클릭 가능하게 설정
                    contentAlignment = Alignment.Center // 내용 중앙 정렬
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_add_group_166),
                        contentDescription = "안건 추가하기", // 적절한 설명 추가
                        modifier = Modifier.size(166.dp) // 이미지 크기 설정
                    )
                    Text(
                        text = "안건 추가하기", // 텍스트 내용
                        color = SteelBlue, // 텍스트 색상
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.Center) // 텍스트를 중앙에 위치
                            .padding(16.dp) // 텍스트 패딩 조정
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewVote1() {
    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
    VoteScreen1(navController = navController) // 초기화한 navController 전달
}