package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.JoinScreenRoute


@Composable
fun AcceptInviteScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current

    // URL 입력 필드 상태 관리
    var textValue by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navController.popBackStack()
                }
            )
        },
        containerColor = lightSkyBlue // Background color
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud() // Cloud background

            // Title and description
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 60.dp, y = -(65.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .height(19.dp)
                        .width(15.3.dp)
                        .graphicsLayer(rotationZ = -120f)
                )

                Text(
                    text = "그룹 링크를 만들어주세요.",
                    modifier = Modifier.padding(start = 16.dp),
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // URL 입력 필드
            Box(
                modifier = Modifier
                    .size(width = 295.dp, height = 55.dp)
                    .offset(x = 35.dp, y = 326.dp)
                    .background(
                        color = LightWhite.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = LightWhite, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                BasicTextField(
                    value = textValue,
                    onValueChange = { newValue -> textValue = newValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .onFocusChanged { state -> isFocused = state.isFocused },
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
                                    text = "URL을 입력해주세요.",
                                    color = SteelBlue,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            // URL 검증 버튼
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = -(40.dp), y = 70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_button_cloud_next_140),
                    contentDescription = "Next Button",
                    modifier = Modifier
                        .clickable {
                            // Pass URL to ViewModel for validation
                            viewModel.setEvent(JoinContract.JoinEvent.ValidateUrl(textValue))
                            navController.navigate(JoinScreenRoute.CHECK.route)
                        }
                        .size(78.dp)
                )
            }

            // URL 검증 결과에 따른 네비게이션 처리
            LaunchedEffect(viewState.isUrlValid) {
                if (viewState.isUrlValid) {
                    navController.navigate(JoinScreenRoute.CHECK.route)
                }
            }

//            // Toast 메시지 처리
//            LaunchedEffect(Unit) {
//                viewModel.effect.collect { sideEffect ->
//                    when (sideEffect) {
//                        is JoinContract.JoinSideEffect._ShowToast -> {
//                            Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
//                        }
//
//                        else -> {}
//                    }
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview7() {
    val navController = NavHostController(LocalContext.current)
    AcceptInviteScreen(navController = navController)
}




