package com.hgh.na_o_man.presentation.ui.main.create

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.homeIcon.ShareGroupButton
import com.hgh.na_o_man.presentation.ui.add.AddGroupActivity
import com.hgh.na_o_man.presentation.ui.main.mypage.MyPageContract
import com.hgh.na_o_man.presentation.ui.sign.SignActivity

@Composable
fun AddMainScreen(
    naviBack: () -> Unit,
    naviAdd: () -> Unit,
    naviJoin: () -> Unit,
    viewModel: AddMainViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "HomeScreen")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AddMainContract.AddMainSideEffect.NaviAdd -> {
                    naviAdd()
                }

                AddMainContract.AddMainSideEffect.NaviBack -> {
                    naviBack()
                }

                AddMainContract.AddMainSideEffect.NaviJoin -> {
                    naviJoin()
                }

                AddMainContract.AddMainSideEffect.NaviSampleUpload -> {
                    context.startActivity(SignActivity.intentUpload(context))
                }

                is AddMainContract.AddMainSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Scaffold(
        containerColor = Color.Transparent
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            //구름 배경 Box
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(12.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { viewModel.setEvent(AddMainContract.AddMainEvent.OnClickBack) }
                    )
            ) {
                EndTopCloud()
                DecorationCloud(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp, bottom = 120.dp)
                        .zIndex(1f)
                )
                DecorationCloud(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 12.dp, bottom = 120.dp)
                        .zIndex(1f)
                        .size(100.dp, 60.dp)
                )
                DecorationCloud(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 260.dp)
                        .zIndex(1f)
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
                    .pointerInput(Unit) {}
            ) {
                ShareGroupButton(title = "공유 그룹 입장") {
                    viewModel.setEvent(AddMainContract.AddMainEvent.OnClickJoin)
                }
                Spacer(modifier = Modifier.width(12.dp))
                ShareGroupButton(title = "공유 그룹 추가") {
                    viewModel.setEvent(AddMainContract.AddMainEvent.OnClickAdd)
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenWithButtonPreView(
) {
//    AddMainScreen({})
}