package com.hgh.na_o_man.presentation.ui.detail.photo_list

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.BackAndSelectAppBar
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.ImageCard
import com.hgh.na_o_man.presentation.component.ImageDialog
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.TopCloud
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.Typography
import kotlinx.coroutines.flow.map

@Composable
fun PhotoListScreen(
    viewModel: PhotoListViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    Log.d("리컴포저블", "PhotoListScreen")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                PhotoListContract.PhotoListSideEffect.NaviBack -> {

                }

                PhotoListContract.PhotoListSideEffect.NaviVote -> {

                }

                is PhotoListContract.PhotoListSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    BackHandler(enabled = viewState.isDialogVisible) {
        viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDialogClosed)
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
                    BackAndSelectAppBar(
                        isMenuClick = viewState.isSelectMode,
                        onStartClick = {
                            viewModel.setEvent(PhotoListContract.PhotoListEvent.OnBackClicked)
                        },
                        onEndClick = {
                            viewModel.setEvent(PhotoListContract.PhotoListEvent.OnSelectModeClicked)
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    TopCloud()
                    DecorationCloud(
                        modifier = Modifier
                            .size(150.dp, 94.dp)
                            .align(Alignment.BottomStart)
                            .offset(x = (-40).dp, y = 20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .padding(start = 40.dp, end = 40.dp, top = 8.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            itemsIndexed(viewState.photoList, key = { _, it ->
                                it.id
                            }) { _, photo ->
                                ImageCard(
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                        .fillMaxWidth()
                                        .aspectRatio(1.25f),
                                    image = photo,
                                    isSelectMode = viewState.isSelectMode,
                                    onClick = {
                                        viewModel.setEvent(
                                            PhotoListContract.PhotoListEvent.OnImageClicked(
                                                it
                                            )
                                        )
                                    },
                                    onSelect = {
                                        viewModel.setEvent(
                                            PhotoListContract.PhotoListEvent.OnImageSelected(
                                                it
                                            )
                                        )
                                    }
                                )

                            }
                        }
                        if (viewState.photoList.count { it.is1 } in 1..5) {
                            ActionButtons(true)
                        } else if (viewState.photoList.count { it.is1 } > 5) {
                            ActionButtons(false)
                        }

                    }
                }

                if (viewState.isDialogVisible) {
                    ImageDialog(
                        image = viewState.dialogPhoto,
                        onCancelButtonClick = { viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDialogClosed) },
                    )
                }

            }
        }
    }
}

@Composable
fun ActionButtons(
    isVoteAvailable: Boolean = true
) {
    Row(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
            .background(Color(0xFFF0F4FF), RoundedCornerShape(14.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(text = "다운 받기", modifier = Modifier.weight(1f))
        if (isVoteAvailable) {
            ActionButton(text = "안건\n부치기", modifier = Modifier.weight(1f))
        }
        ActionButton(text = "삭제하기", modifier = Modifier.weight(1f))
    }
}

@Composable
fun ActionButton(
    text: String, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 11.dp, vertical = 10.dp)
            .height(60.dp)
            .background(Color.White, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = SteelBlue,
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}