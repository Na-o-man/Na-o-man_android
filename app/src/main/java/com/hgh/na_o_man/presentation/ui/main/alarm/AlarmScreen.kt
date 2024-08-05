package com.hgh.na_o_man.presentation.ui.main.alarm

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.domain.model.AlarmDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NoticeIcon.NoticeBox
import com.hgh.na_o_man.presentation.component.NoticeIcon.ReadAllButton
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.AlarmRead
import com.hgh.na_o_man.presentation.component.homeIcon.NoAlarmBox
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox

@Composable
fun AlarmScreen(
    viewModel: AlarmViewModel = hiltViewModel(),
    navigationHome: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블","AlarmScreen")

//    LaunchedEffect(key1 = true) {
//        viewModel.setEvent(AlarmContract.AlarmEvent.InitAlarmScreen)
//    }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            Log.d("알람","로딩중")
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            Log.d("알람","에러")
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Log.d("알람","성공")
            Scaffold(
                topBar = {
                    StartAppBar(
                        onStartClick = {
                            navigationHome()
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->

                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                    DecorationCloud(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 32.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    NoticeBox(title = "알림")
                    Row (
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = -43.dp, y = 60.dp)
                    ) {
                        ReadAllButton(title = "모두 읽음", onClick = {viewModel.setEvent(AlarmContract.AlarmEvent.OnReadAllClicked)})
                        Spacer(modifier = Modifier.width(5.dp))
                        ReadAllButton(title = "전체 삭제", onClick = {viewModel.setEvent(AlarmContract.AlarmEvent.OnDeleteAllClicked)})
                    }
                    if(viewState.alarmList.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            contentAlignment = Alignment.Center
                        ) {
                            NoAlarmBox(message = "알림이 없습니다.")
                        }
                    } else{
                        AlarmListScreen(
                            alarmList = viewState.alarmList,
                            modifier = Modifier.padding(padding)
                        )
                    }
                }

            }
        }
    }
}

//@Composable
//fun AlarmSuccessScreen() {
//    Scaffold(
//        topBar = {
//            StartAppBar(
//                onStartClick = { }
//            )
//        },
//        containerColor = Color.Transparent
//    ) { padding ->
//        Box(modifier = Modifier.fillMaxSize()) {
//            StartTopCloud()
//            DecorationCloud(
//                modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(end = 4.dp, bottom = 32.dp)
//            )
//        }
//
//        Box(
//            modifier = Modifier
//                .padding(padding)
//        ) {
//            Text(
//                "AlarmScreen",
//                modifier = Modifier.align(Alignment.TopCenter),
//                color = Color.Black
//            )
//        }
//    }
//}

@Composable
fun AlarmListScreen(
    alarmList : List<AlarmDummy>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            items(alarmList) { group ->
                AlarmRead(
                    imageRes = group.imageRes,
                    detail = group.detail,
                    date = group.date
                )
            }
        }
    }
}

@Preview
@Composable
fun AlarmPreView(
) {
    AlarmScreen(navigationHome = {})
}