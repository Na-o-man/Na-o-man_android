package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import CloudBtn
import SmallCloudBtn
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.FolderDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.FolderProfile
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.groupdetail.Bigfolder
import com.hgh.na_o_man.presentation.component.groupdetail.GroupInfo
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity.Companion.GROUP_DETAIL
import com.hgh.na_o_man.presentation.ui.main.BottomNavigation
import com.hgh.na_o_man.presentation.ui.main.navigateBottomNavigationScreen


@ExperimentalPagerApi
@Composable
fun GroupDetailFolderScreen(
    navigationMyPage: () -> Unit,
    navigationPhotoList : (Long, Long) -> Unit,
    navigationBack: () -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: GroupDetailFolderViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    val groupId = remember { context.intent.getLongExtra(GROUP_DETAIL, 0L) }
    LaunchedEffect(groupId) {
        viewModel.initGroupId(groupId)
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoList -> {
                    navigationPhotoList(effect.groupId, effect.memberId)
                }
                else -> Unit
            }
        }
    }


    Log.d("리컴포저블", "GroupDetailScreen")

    val dummyFolderData = listOf(
        FolderDummy(R.drawable.ic_example, "Folder1"),
        FolderDummy(R.drawable.ic_example, "Folder2"),
        FolderDummy(R.drawable.ic_example, "Folder3")
    )

    val scope = rememberCoroutineScope()
//    val pagerState = rememberPagerState(initialPage = {dummyFolderData.size})
//    val selectedTabIndex = remember{ derivedStateOf { pagerState.currentPage }  }

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
                    StartEndAppBar(
                        onStartClick = {
                            navigationBack()
                        },
                        onEndClick = {
                            navigationMyPage()
                        }
                    )
                },
                containerColor = Color(0xFFBBCFE5)

            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(100.dp))

                        Box(
                            modifier = Modifier.padding(start = 200.dp)
                        ) {
                            GroupInfo(title = "제목", participantCount = 5, date = "2024.07.20")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)

                        ) {
                            Bigfolder()

                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_half_circle_625),
                                contentDescription = "반원",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .requiredSize(625.dp, 140.dp) // 아이콘 크기를 명시적으로 설정
                                    .offset(y = 57.dp)
                            )
                            FolderProfile(
                                Dummy(),
                                modifier = Modifier
                                    .offset(y = 10.dp)
                                    .requiredSize(160.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 90.dp)
                        ) {
                            SmallCloudBtn(title = "이미지\n분류"){

                            }
                            CloudBtn(title = "다운로드") {
                                // 테스트용 - 삭제해야함
                                viewModel.setEvent( GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked(38L))
                            }
                            SmallCloudBtn(title = "지난 안건"){

                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreView(
) {
    GroupDetailFolderScreen(
        navigationMyPage = {},
        navigationPhotoList = {_ , _ ->},
        navigationBack = {})
}