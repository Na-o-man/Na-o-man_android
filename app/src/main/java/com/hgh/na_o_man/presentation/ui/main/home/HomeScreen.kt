package com.hgh.na_o_man.presentation.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgh.na_o_man.presentation.Utill.composableActivityViewModel
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.CreateAppBar
import com.hgh.na_o_man.presentation.ui.main.MainViewModel
import com.hgh.samplecompose.R

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

        }

        LoadState.ERROR -> {

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
            CreateAppBar(
                onCreateClick = { }
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_test_430),
            contentDescription = stringResource(R.string.back_description),
            tint = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
        )

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