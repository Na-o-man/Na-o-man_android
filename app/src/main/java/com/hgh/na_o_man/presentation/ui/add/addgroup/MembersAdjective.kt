package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.hardware.lights.Light
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
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
fun MembersAdjective(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "MembersAdjective")

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { }
            )
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 110.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Center Image"
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 145.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "모임의 성격을 알려주세요.\n " +
                            "\t\t\t\t(중복 선택 가능)",
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2 + 12.dp.toPx()
                            drawLine(
                                color = LightWhite,
                                start = Offset(-50f, y),
                                end = Offset(size.width + 50f, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            val context = LocalContext.current
            val buttonLabels = listOf("친구", "연인", "여행", "가족", "모임", "동아리", "행사", "나들이", "스냅")
            val buttonCount = buttonLabels.size
            val selectedButtons = remember { mutableStateListOf<Boolean>().apply { repeat(buttonCount) { add(false) } } }
            var inputText by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .offset(y = 90.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                for (row in 0 until 3) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (col in 0 until 3) {
                            val index = row * 3 + col
                            if (index < buttonCount) {
                                val isSelected = selectedButtons[index]
                                Box(
                                    modifier = Modifier
                                        .offset(y = 1.dp)
                                        .width(90.dp)
                                        .height(42.dp)
                                ) {
                                    Button(
                                        onClick = {
                                            selectedButtons[index] = !selectedButtons[index]
                                            val currentAttributes = viewModel.viewState.value.selectedAttributes.toMutableList()
                                            if (selectedButtons[index]) {
                                                currentAttributes.add(buttonLabels[index])
                                            } else {
                                                currentAttributes.remove(buttonLabels[index])
                                            }
                                            viewModel.handleEvents(AddContract.AddEvent.UpdateSelectedAttributes(currentAttributes))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        shape = RoundedCornerShape(20.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (isSelected) Color.Gray.copy(alpha = 0.6f) else LightWhite.copy(alpha = 0.3f),
                                            contentColor = LightWhite
                                        ),
                                        border = BorderStroke(
                                            1.dp,
                                            LightWhite
                                        )
                                    ) {
                                        Text(
                                            text = buttonLabels[index],
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 12.sp,
                                            color = if (isSelected) SteelBlue else LightWhite
                                        )
                                    }
                                }

                                if (col < 2) {
                                    Spacer(modifier = Modifier.width(1.dp))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(17.dp))
                }

                Column {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = {
                            Text(
                                text = "직접 입력",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = LightWhite.copy(alpha = 0.6f)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 10.dp)
                            .border(
                                BorderStroke(1.dp, LightWhite),
                                shape = RoundedCornerShape(50.dp)
                            ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = LightWhite.copy(alpha = 0.3f),
                            unfocusedContainerColor = LightWhite.copy(alpha = 0.3f),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(50.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (inputText.isNotBlank()) {
                                    val index = buttonLabels.indexOf(inputText)
                                    if (index != -1) {
                                        selectedButtons[index] = !selectedButtons[index]
                                        val currentAttributes = viewModel.viewState.value.selectedAttributes.toMutableList()
                                        if (selectedButtons[index])
                                            currentAttributes.add(buttonLabels[index]) else {
                                            currentAttributes.remove(buttonLabels[index])
                                        }
                                        viewModel.handleEvents(AddContract.AddEvent.UpdateSelectedAttributes(currentAttributes))
                                    }
                                    inputText = ""
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(
                        modifier = Modifier
                            .size(110.dp).width(60.dp).height(60.dp)
                            .clickable {
                                val index = buttonLabels.indexOf(inputText)
                                if (index != -1) {
                                    selectedButtons[index] = !selectedButtons[index]
                                    val currentAttributes = viewModel.viewState.value.selectedAttributes.toMutableList()
                                    if (selectedButtons[index]) {
                                        currentAttributes.add(buttonLabels[index])
                                    } else {
                                        currentAttributes.remove(buttonLabels[index])
                                    }
                                    viewModel.handleEvents(AddContract.AddEvent.UpdateSelectedAttributes(currentAttributes))
                                }
                            },
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        val context = LocalContext.current

                        fun initializeButtons(buttonCount: Int) {
                            selectedButtons.clear()
                            repeat(buttonCount) { selectedButtons.add(false) }
                        }

                        // LaunchedEffect to handle side effects from ViewModel
                        LaunchedEffect(Unit) {
                            viewModel.effect.collect { effect ->
                                when (effect) {
                                    is AddContract.AddSideEffect.NavigateToNextScreen -> {
                                        navController.navigate(AddScreenRoute.SPACEINPUT.route)
                                    }
                                    is AddContract.AddSideEffect.ShowToast -> {
                                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                                    }
                                    else -> {}
                                }
                            }
                        }

                        NextAppBar1(
                            onNextClick = {
                                // ViewModel의 createGroup 함수 호출
                                viewModel.handleEvents(AddContract.AddEvent.CreateGroup)
                            },
                            modifier = Modifier
                                .offset(x = 200.dp, y = -(10.dp))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    val navController = NavHostController(context = LocalContext.current)
    MembersAdjective(navController = navController)
}




