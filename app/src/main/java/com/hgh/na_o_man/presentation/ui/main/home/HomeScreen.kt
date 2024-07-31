package com.hgh.na_o_man.presentation.ui.main.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.EventCard
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    navigationMyPage: () -> Unit,
    navigationToMembersInvite: () -> Unit,  // 네비게이션 함수 추가
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val sideEffect by viewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current as Activity

    Log.d("리컴포저블", "HomeScreen")
//    LaunchedEffect(key1 = true) {
//        Log.d("리컴포저블","InitHomeScreen")
//        viewModel.setEvent(HomeContract.HomeEvent.InitHomeScreen)
//    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.HomeSideEffect.NaviMembersInviteScreen -> {
//                Log.d("HomeScreen", "Navigating to MembersInviteScreen")
                    navigationToMembersInvite()
                }

                is HomeContract.HomeSideEffect.NaviAcceptInviteScreen -> {

                }

                is HomeContract.HomeSideEffect.NaviGroupDetail -> {
                    context.startActivity(GroupDetailActivity.newIntent(context, effect.id))
                }

                else -> Unit
            }
        }
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
                            .padding(end = 4.dp, bottom = 120.dp)
                            .zIndex(1f)
                    )
                }
                if (viewState.groupList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        NoGroupBox(message = "아직 공유그룹이 없어요.\n\n그룹을 추가해 주세요.",
                            "공유 그룹 추가하기",
                            onAddGroupInBoxClicked = { viewModel.setEvent(HomeContract.HomeEvent.OnAddGroupInBoxClicked) })
                    }
                } else {
                    GroupListScreen(
                        viewModel = viewModel,
                        groupList = viewState.groupList,
                        modifier = Modifier.padding(padding)
                    )
                }

            }
        }
    }
}

@Composable
fun HomeSuccessScreen(
    navigationMyPage: () -> Unit,
) {
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
                    .zIndex(1f)
            )
        }

        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            Text(
                "HOME",
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.White
            )
        }

    }
}

@Composable
fun GroupListScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    groupList: List<GroupDummy>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            items(groupList) { group ->
                EventCard(
                    imageRes = group.imageRes,
                    title = group.name,
                    participantCount = group.participantCount,
                    date = group.date,
                    onClick = {
                        viewModel.setEvent(HomeContract.HomeEvent.OnGroupListClicked(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenPreView() {
//    val navController = rememberNavController()
//
//    HomeScreen(
//        navigationMyPage = { /* 네비게이션 로직 추가 */ },
//        navigationToMembersInvite = { navController.navigate("members_invite_route") }
//    )
//}