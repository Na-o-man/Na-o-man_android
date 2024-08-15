package com.hgh.na_o_man.presentation.ui.detail.vote_detail

import CloudWhiteBtn
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.BottomStartCloud
import com.hgh.na_o_man.presentation.component.CommonTitle
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.ImageCardWithProfile
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.VoteAfterDialog
import com.hgh.na_o_man.presentation.component.VoteBeforeDialog

@Composable
fun VoteDetailScreen(
    navigationBack: () -> Unit,
    viewModel: VoteDetailViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                VoteDetailContract.VoteDetailSideEffect.NaviBack -> {
                    navigationBack()
                }

                is VoteDetailContract.VoteDetailSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    BackHandler(enabled = viewState.isVoteMode) {
        viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnClickBackOnVote)
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
                    StartAppBar(
                        onStartClick = {
                            if (viewState.isVoteMode) {
                                viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnClickBackOnVote)
                            } else {
                                viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnCLickBack)
                            }
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                    BottomStartCloud(modifier = Modifier.align(Alignment.BottomCenter))
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column {
                        CommonTitle(
                            title = viewState.title,
                            maxLine = 2,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .padding(top = 36.dp),
                            onClick = {

                            })

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .padding(start = 40.dp, end = 40.dp, top = 24.dp, bottom = 40.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),

                            ) {
                            itemsIndexed(viewState.agendas, key = { _, it ->
                                it.agendaPhotoId
                            }) { _, vote ->

                                ImageCardWithProfile(
                                    image = vote.photoInfo,
                                    profiles = vote.voteInfoList.map {
                                        it.profileInfo
                                    },
                                    isSelectMode = false,
                                    isVoteMode = viewState.isVoteMode,
                                    myProfile = viewState.userInfo,
                                    onClick = {
                                        if (viewState.isVoteMode.not()) {
                                            viewModel.setEvent(
                                                VoteDetailContract.VoteDetailEvent.OnCLickNotVoteModeImage(
                                                    vote
                                                )
                                            )
                                        } else {
                                            viewModel.setEvent(
                                                VoteDetailContract.VoteDetailEvent.OnClickVoteModeImage(
                                                    vote
                                                )
                                            )
                                        }
                                    },
                                    onProfileClick = {
                                        viewModel.setEvent(
                                            VoteDetailContract.VoteDetailEvent.OnClickCancelVote(
                                                vote.agendaPhotoId
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                    CloudWhiteBtn(
                        title = if (viewState.isVoteMode.not()) "투표하기" else "→",
                        modifier = Modifier
                            .padding(bottom = 30.dp, end = 24.dp)
                            .size(120.dp, 70.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        if (viewState.isVoteMode.not()) {
                            viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnClickVote)
                        } else {
                            viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnClickFinish)
                        }
                    }

                }

                if (viewState.isDialogVisible) {
                    if (viewState.isVoteMode.not()) {
                        VoteAfterDialog(
                            voteData = viewState.clickAgenda,
                            title = viewState.title,
                            onCancelButtonClick = {
                                viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnDialogClosed)
                            }
                        )
                    } else {
                        VoteBeforeDialog(
                            voteData = viewState.clickAgenda,
                            onCancelButtonClick = {
                                viewModel.setEvent(VoteDetailContract.VoteDetailEvent.OnDialogClosed)
                            }, onVoteClick = { text, id ->
                                viewModel.setEvent(
                                    VoteDetailContract.VoteDetailEvent.OnClickInject(
                                        text = text,
                                        agendaPhotoId = id,
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun VoteDetailPreView() {
    VoteDetailScreen({

    })
}