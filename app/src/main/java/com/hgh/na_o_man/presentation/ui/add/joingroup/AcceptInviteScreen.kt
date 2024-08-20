package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
    navigationHome: () -> Unit
) {
    val context = LocalContext.current as Activity

    var textValue by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var shouldNavigate by remember { mutableStateOf(false) }

    BackHandler {
        context.finish()
    }

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navigationHome()
                }
            )
        },

        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 60.dp, bottom = 120.dp),
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

            Box(
                modifier = Modifier
                    .size(width = 290.dp, height = 55.dp)
                    .align(Alignment.Center)
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

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 30.dp, top = 130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_button_cloud_next_140),
                    contentDescription = "Next Button",
                    modifier = Modifier
                        .clickable {
                            if (textValue.isNotBlank()) {
                                viewModel.setEvent(JoinContract.JoinEvent.ValidateUrl(textValue))
                                shouldNavigate = true
                            } else {
                                Toast.makeText(context, "URL을 입력해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .size(78.dp)
                )
            }

            LaunchedEffect(Unit) {
                viewModel.effect.collect { sideEffect ->
                    when (sideEffect) {
                        is JoinContract.JoinSideEffect._ShowToast -> {
                            Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                        }
                        JoinContract.JoinSideEffect.NavigateToCheckScreen -> {
                            navController.navigate(JoinScreenRoute.CHECK.route)
                            shouldNavigate = false
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}
