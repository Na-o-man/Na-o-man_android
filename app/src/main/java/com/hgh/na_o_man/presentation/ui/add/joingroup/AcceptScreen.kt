package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinEvent.onProfileSelected

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AcceptScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val viewState by viewModel.viewState.collectAsState() // ViewModel의 상태를 수집
    val memberList = viewState.members
    val membersPerPage = 3 // 페이지당 멤버 수
    val pagerState = rememberPagerState(pageCount = { (memberList.size + membersPerPage - 1) / membersPerPage })

    var selectedProfile by remember { mutableStateOf<String?>(null) } // 상태를 var로 변경
//    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            StartAppBar(onStartClick = {
                navController.popBackStack()
            })
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            // 상단에 위치한 Row
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = padding.calculateTopPadding() + 40.dp),
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
                    text = "당신이 누구인지 알려주세요.",
                    modifier = Modifier.padding(start = 16.dp),
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, end = 20.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    val startIndex = page * membersPerPage
                    val endIndex = minOf(startIndex + membersPerPage, memberList.size)
                    val pageMembers = memberList.subList(startIndex, endIndex)
                    
                    AcceptWho1(
                        navController = navController,
                        onProfileSelected = { profile ->
                            selectedProfile = profile // 선택된 프로필을 상태로 업데이트
                            },
                        members = pageMembers, // 현재 페이지의 멤버 정보 전달
                        selectedProfile = selectedProfile, // 현재 선택된 프로필 상태 전달
                        currentPage = page // 현재 페이지 번호 전달
                        )
                }

                PageIndicator(
                    pagerState = pagerState,
                    totalPages = pagerState.pageCount // 페이지 수 설정
                )

                // Next Button Image
                Box(
                    modifier = Modifier
                        .padding(bottom = 50.dp, start = 260.dp)
                        .clickable {
                            if (selectedProfile != null) {
                                // 프로필이 선택되었으면, 다음 화면으로 이동
                                viewModel.handleEvents(onProfileSelected(profileId = 1)) // 프로필 ID를 적절히 설정
                                navController.navigate("next_screen")
                            } else {
                                // 프로필이 선택되지 않았을 때 처리
                                Toast.makeText(context, "프로필을 선택해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        },
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_button_cloud_next_140),
                        contentDescription = "Next Button",
                        modifier = Modifier
                            .size(78.dp, 48.dp),
                    )
                }
            }
        }

//        // 다이얼로그
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text(text = "알림") },
//                text = { Text(text = "프로필 선택이 완료되었습니다.") },
//                confirmButton = {
//                    TextButton(onClick = { showDialog = false }) {
//                        Text("확인")
//                    }
//                }
//            )
//        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    pagerState: PagerState,
    totalPages: Int // 총 페이지 수
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center, // 가로 방향 가운데 정렬
        verticalAlignment = Alignment.CenterVertically // 세로 방향 가운데 정렬
    ) {
        repeat(totalPages) { index ->
            val color = if (pagerState.currentPage == index) DeepBlue else Color.Gray // 현재 페이지에 따라 색상 변경
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape) // 점의 모양
            )
        }
    }
}

