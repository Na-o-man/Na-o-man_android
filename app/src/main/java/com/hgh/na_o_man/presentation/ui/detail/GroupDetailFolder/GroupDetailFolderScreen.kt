package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import CloudBtn
import SmallCloudBtn
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.Utill.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.groupdetail.Bigfolder
import com.hgh.na_o_man.presentation.component.groupdetail.GroupInfo
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailScreen
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailScreenRoute
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListScreen
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListViewModel
import com.hgh.na_o_man.presentation.ui.main.BottomNavigation
import com.hgh.na_o_man.presentation.ui.main.MainViewModel
import com.hgh.na_o_man.presentation.ui.main.navigateBottomNavigationScreen

@Composable
fun GroupDetailFolderScreen(
    navigationMyPage: () -> Unit,
    navigationBack : () -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: GroupDetailFolderViewModel = hiltViewModel(),
    ){
    val viewState by viewModel.viewState.collectAsState()

    Log.d("리컴포저블", "GroupDetailScreen")

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
                        navigateToScreen = {
                            navigationItem -> navigateBottomNavigationScreen(
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

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Spacer(modifier = Modifier.height(100.dp))

                        Box(
                            modifier = Modifier
                                .padding(start = 200.dp)
                        ) {
                            GroupInfo(title = "제목", participantCount = 5, date = "2024.07.20")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Bigfolder()
                        }

                        Spacer(modifier = Modifier.height(90.dp))

                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
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