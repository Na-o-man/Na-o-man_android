package com.hgh.na_o_man.presentation.component.userIcon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue

@Composable
fun UserInfo(
    userName: String,
    profileImagePainter: Painter? = null, // Nullable로 변경
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(360.dp, 125.dp) // 기존 크기 유지
            .padding(15.dp) // 여백 추가
            .clickable(onClick = onClick) // 클릭 가능하도록 설정
            .background(if (isSelected) Color.Gray.copy(0.3f) else Color.Transparent) // 선택 상태에 따라 배경 색상 변경
    ) {

        // 첫 번째 사각형
        Box(
            modifier = Modifier
                .size(320.dp, 70.dp)
                .padding(start = 50.dp, top = 10.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // 그라데이션 사각형
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            LightWhite.copy(alpha = 0.1f), // LightWhite에서 시작하여 점점 밝아짐
                            LightWhite.copy(alpha = 0.8f) // 완전히 밝은 색상으로 그라데이션
                        )
                    ),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(30.dp.toPx())
                )

                // 하얀색 테두리
                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(30.dp.toPx()),
                    style = Stroke(width = 1.dp.toPx()) // 테두리 두께 설정
                )
            }
        }

        // 두 번째 사각형
        Box(
            modifier = Modifier
                .size(320.dp, 198.dp)
                .padding(start = 50.dp, top = 77.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // 그라데이션 사각형
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            LightWhite.copy(alpha = 0.1f),
                            LightWhite.copy(alpha = 0.8f)
                        )
                    ),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(50.dp.toPx())
                )

                // 하얀색 테두리
                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(50.dp.toPx()),
                    style = Stroke(width = 1.5.dp.toPx()) // 테두리 두께 설정
                )
            }
        }

        // 프로필 이미지
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(2.dp, LightWhite, CircleShape)
        ) {
            if (profileImagePainter != null) {
                Image(
                    painter = profileImagePainter, // 프로필 이미지 painter를 사용하여 설정
                    contentDescription = "Avatar $userName",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape), // 원형으로 이미지 클립
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 텍스트
        Text(
            text = userName,
            color = LightWhite,
            modifier = Modifier
                .padding(start = 115.dp, top = 30.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
