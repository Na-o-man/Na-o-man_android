package com.hgh.na_o_man.presentation.ui.detail.vote

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.domain.model.agenda.AgendaDetailInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartBottomCloud
import com.hgh.na_o_man.presentation.component.StartPlusAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.util.OnBottomListener
//import com.hgh.na_o_man.presentation.util.OnBottomListener
import getVoteList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun VoteMainScreen(
    navigationAgenda: (Long) -> Unit,
    navigationBack: () -> Unit,
    navigationVoteDetail: (Long) -> Unit,
    viewModel: VoteMainViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    // 드롭다운 메뉴
    var expanded by remember { mutableStateOf(false) }

    // FocusRequester 초기화
    val focusRequester = remember { FocusRequester() }

    Log.d("리컴포저블", "VoteMainScreen")
    Log.d("VoteMainScreen", "Current view state: $viewState")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                VoteMainContract.VoteMainSideEffect.NaviAgendaAdd -> {
                    navigationAgenda(viewState.groupId)
                }

                VoteMainContract.VoteMainSideEffect.NaviBack -> {
                    navigationBack()
                }

                is VoteMainContract.VoteMainSideEffect.NaviVoteDetail -> {
                    navigationVoteDetail(effect.agendaId)
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.setEvent(VoteMainContract.VoteMainEvent.InitVoteMainScreen)
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
                            viewModel.setEvent(VoteMainContract.VoteMainEvent.OnBackClicked)
                        },
                        onEndClick = {
                             viewModel.setEvent(VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked)
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
                        .fillMaxSize() // 전체 화면을 채우도록 설정
                        .padding(start = 20.dp, top = 60.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                androidx.compose.material3.Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_dropdown_11),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                )
                                BasicTextField(
                                    value = viewState.groupName ?:"",
                                    onValueChange = { },
                                    readOnly = true,
                                    textStyle = TextStyle(
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    ),
                                    modifier = Modifier
                                        .menuAnchor()
                                )
                            }
                            DropdownMenu(
                                modifier = Modifier
                                    .border(3.dp, Color(0xFFBBCFE5), RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFFFFFF))
                                    .padding(horizontal = 24.dp),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                viewState.groupNameList.forEachIndexed { _, member ->
                                    androidx.compose.material3.DropdownMenuItem(
                                        text = {
                                            Text(
                                                member.name,
                                                fontSize = 16.sp,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                        onClick = {
                                            viewModel.setEvent(
                                                VoteMainContract.VoteMainEvent.OnClickDropBoxItem(
                                                    member = member
                                                )
                                            )
                                            expanded = false
                                        },
                                    )
                                }
                            }
                        }
                    }
                }

                if (viewState.voteList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(), // 전체 화면을 채우도록 설정
                        contentAlignment = Alignment.Center // 중앙 정렬
                    ) {
                        NoGroupBox(message = "아직 안건이 없어요.\n플러스 버튼을 눌러\n그룹을 추가해 주세요.",
                            "안건 추가하기",
                            onAddGroupInBoxClicked = { viewModel.setEvent(VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked) })
                    }
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


@Composable
fun VoteListScreen(
    viewModel: VoteMainViewModel = hiltViewModel(),
    voteList:List<AgendaDetailInfoModel>,
    modifier: Modifier = Modifier
) {

    val lazyListState = rememberLazyListState()
    lazyListState.OnBottomListener(2) {
        viewModel.setEvent(VoteMainContract.VoteMainEvent.OnPagingVoteList)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) // 위쪽에 여백을 추가
        ) {
            items(voteList) { vote ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                getVoteList(
                    title = vote.title,
                    images = vote.agendaPhotoInfoList.map { it.url },
                    voteId = vote.agendaId,
                    onClick = { agendaId ->
                        viewModel.setEvent(VoteMainContract.VoteMainEvent.OnAgendaItemClicked(agendaId))
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewVoteMainScreen() {
//    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
//    VoteMainScreen(groupId = 1L ,navigationBack = {}, navigationAgenda = {}) // 초기화한 navController 전달
//}