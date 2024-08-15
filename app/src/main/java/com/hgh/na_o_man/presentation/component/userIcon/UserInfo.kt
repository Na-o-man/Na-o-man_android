package com.hgh.na_o_man.presentation.component.userIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.theme.SteelBlue

@Composable
fun UserInfo(
    userName: String,
    profileImagePainter: Painter, // Painter를 사용하여 이미지를 설정합니다.
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(350.dp, 130.dp)
            .padding(16.dp)
            .clickable(onClick = onClick)
            .background(if (isSelected) SteelBlue.copy(0.3f) else Color.Transparent)
    ) {

        // 프로필 이미지
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
        ) {
            Image(
                painter = profileImagePainter, // 프로필 이미지 painter를 사용하여 설정
                contentDescription = "Avatar $userName",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape), // 원형으로 이미지 클립
                contentScale = ContentScale.Crop
            )
        }

        // 텍스트
        Text(
            text = userName,
            color = SlateGray,
            modifier = Modifier
                .padding(start = 80.dp, top = 20.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
