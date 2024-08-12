package com.hgh.na_o_man.presentation.ui.sign.signin

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.CommonBtn
import com.hgh.na_o_man.presentation.component.Line
import com.hgh.na_o_man.presentation.component.LineSymbol
import com.hgh.na_o_man.presentation.component.SignBtn
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.UriImageCard
import com.hgh.na_o_man.presentation.ui.main.MainActivity

@Composable
fun UploadScreen(
    viewModel: SignViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 2)
    ) { uris: List<Uri> ->
        viewModel.setEvent(SignContract.SignEvent.OnPhotoSelected(uris))
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignContract.SignSideEffect.NaviPhotoPicker -> {
                    imagePickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                    )
                }

                is SignContract.SignSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                is SignContract.SignSideEffect.NaviMain -> {
                    MainActivity.goMain(context)
                }

                else -> {}
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

            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(R.drawable.background_1),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                if (viewState.photos.isEmpty()) {
                    Surface(
                        color = Color(0x4DFFFFFF),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 24.dp)
                            .border(
                                2.dp, Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x00FFFFFF),
                                        Color(0x8CFFFFFF),
                                        Color(0x33FFFFFF),
                                        Color(0xFFFFFFFF),
                                    ),
                                    start = Offset.Zero,
                                    end = Offset.Infinite,
                                ), RoundedCornerShape(24.dp)
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(text = "가이드라인", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(20.dp))
                            LineSymbol(modifier = Modifier.padding(horizontal = 24.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                ExampleImg(
                                    modifier = Modifier.weight(1f),
                                    imageId = R.drawable.background_1,
                                    iconId = R.drawable.ic_yes_13,
                                    "정면 사진"
                                )
                                ExampleImg(
                                    modifier = Modifier.weight(1f),
                                    imageId = R.drawable.background_1,
                                    iconId = R.drawable.ic_yes_13,
                                    "측면 사진"
                                )
                                ExampleImg(
                                    modifier = Modifier.weight(1f),
                                    imageId = R.drawable.background_2,
                                    iconId = R.drawable.ic_no_13,
                                    "너무 작게 나온 사진"
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Line(modifier = Modifier.padding(horizontal = 24.dp))
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "위 가이드 라인을 준수하는 사진\n 2장을 입력해주세요.",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            SignBtn(
                                title = "사진 추가하기",
                                modifier = Modifier
                                    .size(200.dp, 60.dp)
                            ) {
                                viewModel.setEvent(SignContract.SignEvent.OnClickPhotoPicker)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }
                } else {
                    Surface(
                        color = Color(0x4DFFFFFF),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 24.dp)
                            .border(
                                2.dp, Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x00FFFFFF),
                                        Color(0x8CFFFFFF),
                                        Color(0x33FFFFFF),
                                        Color(0xFFFFFFFF),
                                    ),
                                    start = Offset.Zero,
                                    end = Offset.Infinite,
                                ), RoundedCornerShape(24.dp)
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "정면,측면 사진을 각각 한 장씩 추가 해주세요.",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Line(modifier = Modifier.padding(horizontal = 24.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalArrangement = Arrangement.spacedBy(
                                    12.dp,
                                    Alignment.CenterHorizontally
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                itemsIndexed(viewState.photos, key = { _, it ->
                                    it
                                }) { _, photo ->
                                    UriImageCard(
                                        imageUri = photo,
                                        modifier = Modifier.size(128.dp, 128.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            SignBtn(
                                title = "다시 선택하기",
                                modifier = Modifier
                                    .size(200.dp, 60.dp)
                            ) {
                                viewModel.setEvent(SignContract.SignEvent.OnClickPhotoPicker)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            CommonBtn(title = "사진 선택 완료") {
                                viewModel.setEvent(SignContract.SignEvent.OnClickFinish)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExampleImg(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    @DrawableRes iconId: Int,
    text: String,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconId),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.padding(top = 4.dp, end = 4.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview
@Composable
fun UploadScreenPv() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {

        Image(
            painter = painterResource(R.drawable.background_1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Surface(
            color = Color(0x4DFFFFFF),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp)
                .border(
                    2.dp, Brush.linearGradient(
                        colors = listOf(
                            Color(0x00FFFFFF),
                            Color(0x8CFFFFFF),
                            Color(0x33FFFFFF),
                            Color(0xFFFFFFFF),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    ), RoundedCornerShape(24.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "가이드라인", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(20.dp))
                LineSymbol(modifier = Modifier.padding(horizontal = 24.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ExampleImg(
                        modifier = Modifier.weight(1f),
                        imageId = R.drawable.background_1,
                        iconId = R.drawable.ic_yes_13,
                        "정면 사진"
                    )
                    ExampleImg(
                        modifier = Modifier.weight(1f),
                        imageId = R.drawable.background_1,
                        iconId = R.drawable.ic_yes_13,
                        "측면 사진"
                    )
                    ExampleImg(
                        modifier = Modifier.weight(1f),
                        imageId = R.drawable.background_2,
                        iconId = R.drawable.ic_no_13,
                        "너무 작게 나온 사진"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Line(modifier = Modifier.padding(horizontal = 24.dp))
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "위 가이드 라인을 준수하는 사진\n 2장을 입력해주세요.",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(20.dp))
                SignBtn(
                    title = "사진 추가하기"
                ) {

                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
