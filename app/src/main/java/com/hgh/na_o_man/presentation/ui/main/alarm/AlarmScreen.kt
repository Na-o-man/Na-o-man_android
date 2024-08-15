package com.hgh.na_o_man.presentation.ui.main.alarm

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hgh.na_o_man.domain.model.AlarmDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NoticeIcon.AlarmButton
import com.hgh.na_o_man.presentation.component.NoticeIcon.NoticeBox
import com.hgh.na_o_man.presentation.component.NoticeIcon.ReadAllUnClickableButton
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.AlarmNotRead
import com.hgh.na_o_man.presentation.component.homeIcon.AlarmRead
import com.hgh.na_o_man.presentation.component.homeIcon.NoAlarmBox
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.util.OnBottomListener

@Composable
fun AlarmScreen(
    viewModel: AlarmViewModel = hiltViewModel(),
    navigationHome: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "AlarmScreen")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.setEvent(AlarmContract.AlarmEvent.InitAlarmScreen)
    }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            Log.d("알람", "로딩중")
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            Log.d("알람", "에러")
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Log.d("알람", "성공")
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

                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    NoticeBox(title = "알림")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 40.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        val hasUnread = viewState.alarmList.any { !it.isRead }
                        if (hasUnread) {
                            AlarmButton(title = "모두 읽음", onClick = {
                                Log.d("AlarmButton", "모두 읽음 버튼 클릭")
                                viewModel.setEvent(AlarmContract.AlarmEvent.OnReadAllClicked)
                            })
                        } else {
                            ReadAllUnClickableButton(title = "모두 읽음")
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        AlarmButton(title = "전체 삭제", onClick = {
                            Log.d("AlarmButton", "전체 삭제 버튼 클릭")
                            viewModel.setEvent(AlarmContract.AlarmEvent.OnDeleteAllClicked)
                        })
                    }
                    if (viewState.alarmList.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),
                            contentAlignment = Alignment.Center
                        ) {
                            NoAlarmBox(message = "알림이 없습니다.")
                        }
                    } else {
                        AlarmListScreen(
                            alarmList = viewState.alarmList,
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
    viewModel: AlarmViewModel = hiltViewModel(),
    alarmList: List<AlarmDummy>,
    modifier: Modifier = Modifier
) {

    val lazyListState = rememberLazyListState()
    lazyListState.OnBottomListener(2) {
        viewModel.setEvent(AlarmContract.AlarmEvent.OnPagingAlarmList)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp),
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            items(alarmList) { group ->
                if (group.isRead == false) {
                    AlarmNotRead(
                        imageRes = group.imageRes,
                        detail = group.detail,
                        date = group.date
                    )
                } else {
                    AlarmRead(
                        imageRes = group.imageRes,
                        detail = group.detail,
                        date = group.date
                    )
                }
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