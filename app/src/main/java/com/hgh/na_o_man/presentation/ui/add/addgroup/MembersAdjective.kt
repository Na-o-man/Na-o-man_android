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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    val context = LocalContext.current
    Log.d("리컴포저블", "MembersAdjective")

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navController.popBackStack()
                }
            )
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp), // 비율 기반 padding
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
                    .padding(top = 90.dp), // 비율 기반 padding
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
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }

            val context = LocalContext.current
            val buttonLabels = listOf("친구", "연인", "여행", "가족", "모임", "동아리", "행사", "나들이", "스냅")
            val buttonCount = buttonLabels.size
            val selectedButtons =
                remember { mutableStateListOf<Boolean>().apply { repeat(buttonCount) { add(false) } } }
            var inputText by remember { mutableStateOf("") }
            val selectedAttributes =
                viewModel.viewState.collectAsState().value.selectedAttributes.toMutableList()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 175.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 버튼 UI
                buttonLabels.chunked(3).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowItems.forEachIndexed { rowIndex, label ->
                            val actualIndex = buttonLabels.indexOf(label)
                            val isSelected = selectedButtons[actualIndex]
                            Button(
                                onClick = {
                                    // 버튼 선택 상태 업데이트
                                    selectedButtons[actualIndex] = !isSelected

                                    // 속성 업데이트
                                    if (selectedButtons[actualIndex]) {
                                        selectedAttributes.add(label)
                                    } else {
                                        selectedAttributes.remove(label)
                                    }

                                    viewModel.handleEvents(
                                        AddContract.AddEvent.UpdateSelectedAttributes(
                                            selectedAttributes
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp), // 버튼 사이 간격 조정
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) Color.Gray.copy(alpha = 0.3f) else LightWhite.copy(
                                        alpha = 0.3f
                                    ),
                                    contentColor = LightWhite
                                ),
                                border = BorderStroke(1.dp, LightWhite)
                            ) {
                                Text(
                                    text = label,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = if (isSelected) SteelBlue else LightWhite
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp)) // Row 간 간격 조정
                }

                // 입력 필드
                TextField(
                    value = inputText,
                    onValueChange = { newText ->
                        inputText = newText

                        // 텍스트 입력이 있을 경우, 다른 텍스트 항목은 제거
                        if (inputText.isNotBlank()) {
                            val nonButtonTextAttributes =
                                selectedAttributes.filter { it !in buttonLabels }
                            if (nonButtonTextAttributes.isEmpty()) {
                                selectedAttributes.add(inputText)
                            } else {
                                selectedAttributes.removeAll(nonButtonTextAttributes)
                                selectedAttributes.add(inputText)
                            }

                            viewModel.handleEvents(
                                AddContract.AddEvent.UpdateSelectedAttributes(selectedAttributes)
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = "직접 입력",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = LightWhite.copy(alpha = 0.6f)
                        )
                    },
                    modifier = Modifier
                        .height(205.dp)
                        .padding(start = 20.dp, end = 20.dp, bottom = 120.dp)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp) // 비율 기반 padding
                        .border(
                            BorderStroke(1.dp, LightWhite),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = LightWhite,
                        focusedTextColor = LightWhite,
                        cursorColor = LightWhite,
                        focusedContainerColor = LightWhite.copy(alpha = 0.3f),
                        unfocusedContainerColor = LightWhite.copy(alpha = 0.3f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(50.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (inputText.isNotBlank() && inputText !in selectedAttributes) {
                                selectedAttributes.add(inputText)
                                viewModel.handleEvents(
                                    AddContract.AddEvent.UpdateSelectedAttributes(selectedAttributes)
                                )
                                inputText = ""  // 입력 필드 초기화
                            }
                        }
                    )
                )

                Spacer(modifier = Modifier.height(16.dp)) // 비율 기반 spacing

                val selectedAttributes =
                    viewModel.viewState.collectAsState().value.selectedAttributes

                NextAppBar1(
                    onNextClick = {
                        // 선택된 버튼이 하나라도 있거나 텍스트 입력 필드에 값이 있는 경우만 이동
                        val hasSelectedButtons = selectedAttributes.size > 0
                        val hasTextInput = inputText.isNotBlank()

                        if (hasSelectedButtons || hasTextInput) {
                            navController.navigate(AddScreenRoute.SPACEINPUT.route)
                        } else {
                            Toast.makeText(context, "하나 이상의 항목을 선택하거나 텍스트를 입력해야 합니다.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.offset( y = -(50.dp))
                )
            }
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
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    val navController = NavHostController(context = LocalContext.current)
    MembersAdjective(navController = navController)
}

