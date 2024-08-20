package com.hgh.na_o_man.presentation.ui.detail.photo_list

import CloudWhiteBtn
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.BackAndSelectAppBar
import com.hgh.na_o_man.presentation.component.BackAppBar
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.ImageCard
import com.hgh.na_o_man.presentation.component.ImageDialog
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.TopCloud
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.Typography
import com.hgh.na_o_man.presentation.util.OnBottomListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen(
    navigationBack: () -> Unit,
    navigationAgenda: (List<PhotoInfoModel>) -> Unit,
    viewModel: PhotoListViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    var expanded by remember { mutableStateOf(false) }

    val lazyGridState = rememberLazyGridState()
    lazyGridState.OnBottomListener(2) {
        viewModel.setEvent(PhotoListContract.PhotoListEvent.OnPagingPhoto)
    }

    Log.d("리컴포저블", "PhotoListScreen")

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(PhotoListContract.PhotoListEvent.InitPhotoListScreen)
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                PhotoListContract.PhotoListSideEffect.NaviBack -> {
                    navigationBack()
                }

                is PhotoListContract.PhotoListSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                PhotoListContract.PhotoListSideEffect.NaviAgenda -> {
                    navigationAgenda(viewState.selectPhotoList)
                }
            }
        }
    }

    BackHandler(enabled = viewState.isDialogVisible) {
        viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDialogClosed)
    }

    BackHandler(enabled = viewState.isAgenda) {
        viewModel.setEvent(PhotoListContract.PhotoListEvent.OnAgendaClicked)
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
                    if (viewState.isAgenda) {
                        BackAppBar {
                            viewModel.setEvent(PhotoListContract.PhotoListEvent.OnBackClicked)
                        }
                    } else {
                        BackAndSelectAppBar(
                            isMenuClick = viewState.isSelectMode,
                            onStartClick = {
                                viewModel.setEvent(PhotoListContract.PhotoListEvent.OnBackClicked)
                            },
                            onEndClick = {
                                viewModel.setEvent(PhotoListContract.PhotoListEvent.OnSelectModeClicked)
                            }
                        )
                    }
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
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
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
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_dropdown_11),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                    )
                                    BasicTextField(
                                        value = viewState.memberList.find {
                                            it.profileId == viewState.profileId
                                        }?.name ?: "",
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
                                    viewState.memberList.filter {
                                        it.profileId != viewState.profileId
                                    }.forEachIndexed { index, member ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    member.name,
                                                    textAlign = TextAlign.Center,
                                                    fontSize = 16.sp,
                                                    modifier = Modifier.fillMaxWidth()
                                                )
                                            },
                                            onClick = {
                                                viewModel.setEvent(
                                                    PhotoListContract.PhotoListEvent.OnClickDropBoxItem(
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
                        //Text(text = "전체", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        LazyVerticalGrid(
                            state = lazyGridState,
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .padding(start = 40.dp, end = 40.dp, top = 24.dp)
                                .weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(viewState.photoList, key = { _, it ->
                                it.photoId
                            }) { _, photo ->

                                val isLastItem = viewState.photoList.last() == photo
                                val modifier = if (isLastItem) {
                                    Modifier.padding(bottom = 70.dp)
                                } else {
                                    Modifier.padding(bottom = 8.dp)
                                }

                                ImageCard(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1.20f),
                                    image = photo,
                                    isSelectMode = viewState.isSelectMode || viewState.isAgenda,
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
                    }
                    if (viewState.photoList.count { it.isSelected } > 0) {
                        PhotoMenu(
                            isMine = viewState.isMine,
                            isAgenda = viewState.isAgenda,
                            modifier = Modifier.align(alignment = Alignment.BottomCenter),
                            onCLickDown = {
                                viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDownloadClicked)
                            },
                            onClickDelete = {
                                viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDeleteClicked)
                            },
                            onClickAgenda = {
                                viewModel.setEvent(PhotoListContract.PhotoListEvent.OnAgendaClicked)
                            }
                        )
                    }
                }

                if (viewState.isDialogVisible) {
                    ImageDialog(
                        image = viewState.dialogPhoto,
                        onCancelButtonClick = { viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDialogClosed) },
                        { modifier ->
                            PhotoMenu(
                                isMine = viewState.isMine,
                                modifier = modifier,
                                onCLickDown = {
                                    viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDownloadClicked)
                                },
                                onClickDelete = {
                                    viewModel.setEvent(PhotoListContract.PhotoListEvent.OnDeleteClicked)
                                },
                                onClickAgenda = {
                                    viewModel.setEvent(PhotoListContract.PhotoListEvent.OnAgendaClicked)
                                })
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun PhotoMenu(
    isMine: Boolean = false,
    isAgenda: Boolean = false,
    modifier: Modifier = Modifier,
    onCLickDown: () -> Unit,
    onClickDelete: () -> Unit = {},
    onClickAgenda: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        Surface(
            color = Color(0x4DFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .align(Alignment.BottomEnd)
                .height(40.dp)
                .border(
                    2.dp, Brush.linearGradient(
                        colors = listOf(
                            Color(0x00FFFFFF),
                            Color(0x8CFFFFFF),
                            Color(0x33FFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0x00FFFFFF),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    ),
                    RoundedCornerShape(0.dp)
                )
        ) { }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (isAgenda) {
                CloudWhiteBtn(title = "사진 추가") {
                    onClickAgenda()
                }
            } else {
                CloudWhiteBtn(title = "다운 받기") {
                    onCLickDown()
                }
                if (isMine) {
                    Spacer(modifier = Modifier.width(20.dp))
                    CloudWhiteBtn(title = "삭제하기") {
                        onClickDelete()
                    }
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

@Preview
@Composable
fun photoList() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {
        Surface(
            color = Color(0x4DFFFFFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .align(Alignment.BottomEnd)
                .height(40.dp)
                .border(
                    2.dp, Brush.linearGradient(
                        colors = listOf(
                            Color(0x00FFFFFF),
                            Color(0x8CFFFFFF),
                            Color(0x33FFFFFF),
                            Color(0xFFFFFFFF),
                            Color(0x00FFFFFF),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    ),
                    RoundedCornerShape(0.dp)
                )
        ) { }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CloudWhiteBtn(title = "다운 받기", modifier = Modifier)
            Spacer(modifier = Modifier.width(20.dp))
            CloudWhiteBtn(title = "삭제하기")
        }
    }
}