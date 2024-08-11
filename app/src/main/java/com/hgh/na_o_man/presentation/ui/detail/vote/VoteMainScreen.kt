package com.hgh.na_o_man.presentation.ui.detail.vote

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.PlusAppBar
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartBottomCloud
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StartPlusAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.component.voteIcon.getVoteList
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity.Companion.GROUP_DETAIL
import com.hgh.na_o_man.presentation.ui.main.home.GroupListScreen
import com.hgh.na_o_man.presentation.ui.main.home.HomeContract


@Composable
fun VoteMainScreen(
    groupId : Long,
    navigationAgenda: () -> Unit,
    navigationBack: () -> Unit,
    viewModel: VoteMainViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    Log.d("리컴포저블", "VoteMainScreen")

    LaunchedEffect(groupId) {
        Log.d("VoteMainScreen", "Received groupId: $groupId")
        viewModel.initGroupId(groupId)
    }


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
                    StartPlusAppBar(
                        onStartClick = {
                        /*TODO*/
                        },
                        onEndClick = {
                        /*TODO*/
                        }
                    )
                },
                containerColor = lightSkyBlue
            ) { padding ->
                // 구름 배경 Box
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
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
                        .fillMaxSize(), // 전체 화면을 채우도록 설정
                    contentAlignment = Alignment.Center // 중앙 정렬
                ) {
                    if (viewState.voteList.isEmpty()) {
                        NoGroupBox(message = "아직 안건이 없어요.\n플러스 버튼을 눌러\n그룹을 추가해 주세요.",
                            "안건 추가하기",
                            onAddGroupInBoxClicked = { viewModel.setEvent(VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked) })
                    } else {
                        VoteListScreen(
                            viewModel = viewModel,
                            voteList = viewState.voteList,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun VoteListScreen(
    viewModel: VoteMainViewModel = hiltViewModel(),
    voteList:List<VoteDummy>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            items(voteList) { vote ->
                getVoteList(
                    title = vote.title,
                    images = vote.images)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewVoteMainScreen() {
    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
    VoteMainScreen(groupId = 1L ,navigationBack = {}, navigationAgenda = {}) // 초기화한 navController 전달
}