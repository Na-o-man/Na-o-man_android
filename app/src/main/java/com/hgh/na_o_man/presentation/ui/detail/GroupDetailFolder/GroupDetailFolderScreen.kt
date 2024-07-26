package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import CloudBtn
import SmallCloudBtn
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.FolderDummy
import com.hgh.na_o_man.domain.model.GroupDummy
//import com.hgh.na_o_man.presentation.Util.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.FolderProfile
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.UserProfile
import com.hgh.na_o_man.presentation.component.groupdetail.Bigfolder
import com.hgh.na_o_man.presentation.component.groupdetail.GroupInfo
import com.hgh.na_o_man.presentation.component.homeIcon.EventCard
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailScreen
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailScreenRoute
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListScreen
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListViewModel
import com.hgh.na_o_man.presentation.ui.main.BottomNavigation
import com.hgh.na_o_man.presentation.ui.main.MainViewModel
import com.hgh.na_o_man.presentation.ui.main.navigateBottomNavigationScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupDetailFolderScreen(
    navigationMyPage: () -> Unit,
    navigationBack: () -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: GroupDetailFolderViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    Log.d("리컴포저블", "GroupDetailScreen")

    val dummyFolderData = listOf(
        FolderDummy(R.drawable.ic_example, "Folder1"),
        FolderDummy(R.drawable.ic_example, "Folder2"),
        FolderDummy(R.drawable.ic_example, "Folder3")
    )

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {dummyFolderData.size})
    val selectedTabIndex = remember{ derivedStateOf { pagerState.currentPage }  }

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
                bottomBar = {
                    BottomNavigation(
                        currentDestination = navController.currentDestination,
                        navigateToScreen = { navigationItem ->
                            navigateBottomNavigationScreen(
                                navController = navController,
                                navigationItem = navigationItem,
                            )
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
                            FolderProfile(Dummy(),
                                modifier = Modifier
                                    .offset(y = 10.dp)
                                    .requiredSize(160.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 90.dp)
                        ) {
                            SmallCloudBtn(title = "이미지\n분류")
                            CloudBtn(title = "다운로드")
                            SmallCloudBtn(title = "지난 안건")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreView(
) {
    GroupDetailFolderScreen(
        navigationMyPage = {},
        navigationBack = {})
}