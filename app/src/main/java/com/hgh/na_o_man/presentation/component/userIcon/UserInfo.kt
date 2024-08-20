package com.hgh.na_o_man.presentation.component.userIcon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.presentation.theme.LightNavy
import com.hgh.na_o_man.presentation.theme.LightWhite

@Composable
fun UserInfo(
    userName: String,
    profileImagePainter: Painter? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val imageColorFilter = if (isSelected) ColorFilter.tint(LightNavy.copy(0.7f)) else null

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(15.dp)
            .clickable(onClick = onClick),
    ) {

        //이름표
        Box(
            modifier = Modifier
                .size(275.dp, 70.dp)
                .padding(start = 50.dp, top = 10.dp),
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            LightWhite.copy(alpha = 0.1f),
                            LightWhite.copy(alpha = 0.8f)
                        )
                    ),

                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(30.dp.toPx()),
                    colorFilter = imageColorFilter
                )

                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(30.dp.toPx()),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
        }

        // 이름표 하단 이미지
        Box(
            modifier = Modifier
                .size(275.dp, 198.dp)
                .padding(start = 50.dp, top = 77.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRoundRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            LightWhite.copy(alpha = 0.1f),
                            LightWhite.copy(alpha = 0.8f)
                        )
                    ),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(50.dp.toPx()),
                    colorFilter = imageColorFilter
                )

                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(50.dp.toPx()),
                    style = Stroke(width = 1.5.dp.toPx())
                )
            }
        }

        // 프로필 이미지
        Box(
            modifier = Modifier
                .size(99.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .border(2.dp, LightWhite, CircleShape)
                .align(Alignment.CenterStart)
        ) {
            if (profileImagePainter != null) {
                Image(
                    painter = profileImagePainter,
                    contentDescription = "Avatar $userName",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
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
