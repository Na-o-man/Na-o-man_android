package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute


@Composable
fun MembersSpace(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    navigationBack: () -> Unit,
) {
    Log.d("리컴포저블", "members_space")
    var textValue by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navigationBack()
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(top = 130.dp, end = 20.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                NextAppBar1(
                    onNextClick = {
                        if (textValue.isNotEmpty()) {
                            viewModel.handleEvents(AddContract.AddEvent.CreateGroup)
                            navController.navigate(AddScreenRoute.LOADING.route)
                        }
                        else {
                            AddContract.AddSideEffect.ShowToast("텍스트를 입력해주세요.")
                        }
                    },
                )
            }
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 230.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Center Image"
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 160.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "어디에서 찍은 사진인가요?",
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2 + 10.dp.toPx()
                            drawLine(
                                color = LightWhite,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // 텍스트창
            Box(
                modifier = Modifier
                    .size(295.dp, 55.dp)
                    .background(
                        color = LightWhite.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = LightWhite, shape = RoundedCornerShape(20.dp)
                    )
            ) {

                BasicTextField(
                    value = textValue,
                    onValueChange = { newValue ->
                        textValue = newValue
                        viewModel.updatePlace(newValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .onFocusChanged { state ->
                            isFocused = state.isFocused
                        },
                    textStyle = TextStyle(
                        color = SteelBlue,
                        background = Color.Transparent,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    ),
                    cursorBrush = SolidColor(SteelBlue),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            contentAlignment = Alignment.Center
                        ) {
                            if (textValue.isEmpty() && !isFocused) {
                                Text(
                                    text = "공간을 입력해주세요.",
                                    color = SteelBlue,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                val context = LocalContext.current

                LaunchedEffect(Unit) {
                    viewModel.effect.collect { effect ->
                        when (effect) {
                            is AddContract.AddSideEffect.NavigateToNextScreen -> {
                                navController.navigate(AddScreenRoute.LOADING.route)
                            }

                            is AddContract.AddSideEffect.ShowToast -> {
                                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

