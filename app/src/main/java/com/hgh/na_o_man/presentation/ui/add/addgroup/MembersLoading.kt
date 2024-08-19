package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute
import kotlinx.coroutines.delay


@Composable
fun MembersLoading(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "members_loading")

    Scaffold(
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            StartTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val rotationState = remember { Animatable(0f) }

            // 로딩
            LaunchedEffect(Unit) {
                while (true) {
                    rotationState.animateTo(
                        targetValue = 360f,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                    rotationState.snapTo(0f)
                }
            }

            // 로딩 이미지
            Box(
                modifier = Modifier
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Center Image",
                    modifier = Modifier
                        .graphicsLayer(rotationZ = rotationState.value) // 회전 적용
                        .graphicsLayer(rotationZ = -120f)
                        .size(60.dp)
                )
            }

            val offsetY = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                while (true) {
                    offsetY.animateTo(
                        targetValue = 10f,
                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                    )
                    offsetY.animateTo(
                        targetValue = -10f,
                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                    )
                    offsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                    )
                }
            }

            // 텍스트
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "그룹 생성 중입니다...",
                    modifier = Modifier.graphicsLayer(translationY = offsetY.value),
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }

        LaunchedEffect(Unit) {
            delay(300)
            navController.navigate(AddScreenRoute.FOLDER.route)
        }
    }
}


