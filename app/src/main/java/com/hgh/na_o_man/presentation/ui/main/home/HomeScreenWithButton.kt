package com.hgh.na_o_man.presentation.ui.main.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.GroupDummy
//import com.hgh.na_o_man.presentation.Utill.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.EventCard
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.component.homeIcon.ShareGroupButton
import com.hgh.na_o_man.presentation.ui.main.MainScreenRoute
import com.hgh.na_o_man.presentation.ui.main.MainViewModel
import com.hgh.na_o_man.presentation.util.composableActivityViewModel

@Composable
fun HomeScreenWithButton(
    navigationMyPage: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "HomeScreen")
//    LaunchedEffect(key1 = true) {
//        Log.d("리컴포저블","InitHomeScreen")
//        viewModel.setEvent(HomeContract.HomeEvent.InitHomeScreen)
//    }

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
                    EndAppBar(
                        onEndClick = {
                            navigationMyPage()
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                //구름 배경 Box
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                    DecorationCloud(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 190.dp)
                            .zIndex(1f))
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    NoGroupBox(message = "아직 공유그룹이 없어요.\n\n그룹을 추가해 주세요.","공유 그룹 추가하기")

                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = -100.dp)
                    ) {
                        ShareGroupButton(title = "공유 그룹 입장")
                        ShareGroupButton(title = "공유 그룹 추가")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenWithButtonPreView(
) {
    HomeScreenWithButton(navigationMyPage = {})
}