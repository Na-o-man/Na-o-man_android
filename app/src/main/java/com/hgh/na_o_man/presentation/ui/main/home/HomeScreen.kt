package com.hgh.na_o_man.presentation.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.presentation.Utill.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndAppBar
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.ui.main.MainViewModel

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel = composableActivityViewModel(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(HomeContract.HomeEvent.InitHomeScreen)
    }

    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            HomeSuccessScreen()
        }
    }
}

@Composable
fun HomeSuccessScreen() {
    Scaffold(
        topBar = {
            EndAppBar(
                onCreateClick = { }
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        //구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            Text(
                "HOME",
                modifier = Modifier.align(Alignment.TopCenter),
                color = Color.White
            )
        }

    }
}


@Preview
@Composable
fun PreView(
) {
    HomeSuccessScreen()
}