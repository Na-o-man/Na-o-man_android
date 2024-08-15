package com.hgh.na_o_man.presentation.ui.detail.agenda

import CloudWhiteBtn
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.presentation.component.AgendaPhotos
import com.hgh.na_o_man.presentation.component.BottomStartCloud
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.TitleDialog
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.ui.detail.AGENDA_PHOTOS

@Composable
fun AddAgendaScreen(
    navigationBack: () -> Unit,
    navigationPhotoList: (Long, Long, Long) -> Unit,
    navController: NavController,
    viewModel: AddAgendaViewModel = hiltViewModel(),
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val agendaPhotos = remember {
        savedStateHandle?.get<List<PhotoInfoModel>>(AGENDA_PHOTOS)?.map {
            it.copy(isSelected = false)
        } ?: listOf(PhotoInfoModel())
    }
    var agendaTitle by remember { mutableStateOf("") }
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AddAgendaContract.AddAgendaSideEffect.NaviBack -> {
                    navigationBack()
                }

                AddAgendaContract.AddAgendaSideEffect.NaviPhotoList -> {
                    navigationPhotoList(viewState.groupId, 100L, -1L)
                }

                is AddAgendaContract.AddAgendaSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    viewModel.setEvent(AddAgendaContract.AddAgendaEvent.OnBackClicked)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    color = Color(0x66FFFFFF),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .border(
                            2.dp, Brush.linearGradient(
                                colors = listOf(
                                    Color(0x00FFFFFF),
                                    Color(0xCCFFFFFF),
                                    Color(0x33FFFFFF),
                                    Color(0xB3FFFFFF),
                                ),
                                start = Offset.Zero,
                                end = Offset.Infinite,
                            ), RoundedCornerShape(14.dp)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(vertical = 16.dp, horizontal = 16.dp),

                        ) {
                        Text(
                            text = "안건",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        BasicTextField(
                            value = agendaTitle,
                            onValueChange = {
                                agendaTitle = if (it.length <= 30) {
                                    it
                                } else {
                                    it.take(30)
                                }
                            },
                            textStyle = TextStyle(color = Color(0xFF1D3A72)),
                            singleLine = false,
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .padding(4.dp)// Adjust padding as needed
                                ) {
                                    if (agendaTitle.isEmpty()) {
                                        Text(
                                            text = "안건 제목을 입력해주세요",
                                            color = Color.Gray,
                                            fontSize = 12.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            },
                            modifier = Modifier
                                .wrapContentHeight()
                                .weight(1f)
                                .padding(start = 12.dp)
                        )
                    }
                }
                if (agendaPhotos.size < 2) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                            contentDescription = "Center Image",
                            modifier = Modifier
                                .size(16.dp)
                                .graphicsLayer(rotationZ = -120f)
                        )
                        Text(
                            text = "+를 눌러 사진을 추가해주세요.\n ",
                            color = LightWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(
                                top = 15.dp,
                                start = 30.dp
                            ) // 텍스트와 이미지 간의 간격 설정
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                AgendaPhotos(
                    images = agendaPhotos.ifEmpty { listOf(PhotoInfoModel()) },
                    onClick = {
                        viewModel.setEvent(AddAgendaContract.AddAgendaEvent.OnAddPhotosClicked)
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                CloudWhiteBtn(
                    title = "안건 추가",
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .size(120.dp, 70.dp)
                        .align(Alignment.End)
                ) {
                    if (agendaPhotos.size >= 2) {
                        viewModel.setEvent(
                            AddAgendaContract.AddAgendaEvent.OnAddAgendaClicked(
                                title = agendaTitle,
                                photos = agendaPhotos,
                            )
                        )
                    } else {
                        viewModel.setEvent(AddAgendaContract.AddAgendaEvent.OnDialogOpened)
                    }
                }
            }
        }

        if (viewState.isDialogVisibility) {
            TitleDialog(
                title = "안건은 두 장 이상의 사진이 필요합니다. 사진을 더 선택해 주세요.",
                onCancelButtonClick = {
                    viewModel.setEvent(AddAgendaContract.AddAgendaEvent.OnDialogClosed)
                }
            )
        }
    }
}

@Preview
@Composable
fun CVPreView() {
    //  AddAgendaScreen({},{_,_ ->})
}