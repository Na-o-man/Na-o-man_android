package com.hgh.na_o_man.presentation.ui.main.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.GroupDummy
import com.hgh.na_o_man.presentation.Utill.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.EventCard
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.ui.main.MainScreenRoute
import com.hgh.na_o_man.presentation.ui.main.MainViewModel
import kotlin.coroutines.coroutineContext

@Composable
fun HomeScreen(
    navigationMyPage: () -> Unit,
    mainViewModel: MainViewModel = composableActivityViewModel(),
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
                }
                if(viewState.groupList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        NoGroupBox(message = "아직 공유그룹이 없어요.\n\n그룹을 추가해 주세요.")
                    }
                } else{
                    GroupListScreen(
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
    groupList : List<GroupDummy>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
            ) {
            groupList.forEach { group ->
                EventCard(
                    imageRes = group.imageRes,
                    title = group.name,
                    participantCount = group.participantCount,
                    date = group.date
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

//@Preview
//@Composable
//fun PreView(
//) {
//    HomeSuccessScreen(navController)
//}