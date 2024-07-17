package com.hgh.na_o_man.presentation.ui.main.mypage

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.ui.main.home.HomeViewModel

@Composable
fun MyPageScreen(
    navigationBack: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MyPageScreen")

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
                            navigationBack()
                        }
                    )
                },
                containerColor = Color.Transparent
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    StartTopCloud()
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
                    Text(
                        "MyPage",
                        modifier = Modifier.align(Alignment.TopCenter),
                        color = Color.Black
                    )
                }

            }
        }
    }
}

@Composable
fun MyPageSuccessScreen(
    navigationBack: () -> Unit,
) {

}