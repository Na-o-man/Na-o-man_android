package com.hgh.na_o_man.presentation.component.userIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SlateGray
import com.hgh.na_o_man.presentation.theme.SteelBlue

@Composable
fun UserInfo(
    userName: String,
    profileImageRes: Int,
    isSelected: Boolean = false, // 선택 상태 추가
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(350.dp, 130.dp) // 기존 크기 유지
            .padding(16.dp) // 여백 추가
            .clickable(onClick = onClick) // 클릭 가능하도록 설정
            .background(if (isSelected) SteelBlue.copy(0.3f) else Color.Transparent) // 선택 상태에 따라 배경 색상 변경
    ) {

        // 첫 번째 이미지
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
            contentDescription = null,
            modifier = Modifier
                .height(70.dp)
                .width(320.dp)
                .padding(start = 50.dp, top = 10.dp)
                .background(Color.Transparent) // 배경을 투명하게 설정
                .clip(RoundedCornerShape(30.dp)), // 둥근 모서리 적용
            contentScale = ContentScale.FillBounds, // 이미지 비율 무시하고 크기 맞춤
                    colorFilter = if (isSelected) ColorFilter.tint(SteelBlue.copy(0.4f)) else null // 선택 상태에 따라 색상 필터 적용
        )

        // 두 번째 이미지
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .width(320.dp)
                .padding(start = 50.dp, top = 77.dp) // 여전히 겹치게 설정
                .background(Color.Transparent) // 배경을 투명하게 설정
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.FillBounds, // 이미지 비율 무시하고 크기 맞춤
            colorFilter = if (isSelected) ColorFilter.tint(SteelBlue.copy(0.6f)) else null // 선택 상태에 따라 색상 필터 적용
        )

        // 프로필 이미지
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 0.dp) // 여백 추가
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = profileImageRes),
                contentDescription = "Profile",
                modifier = Modifier
                    .background(Color.Transparent) // 배경을 투명하게 설정
                    .height(97.dp)
                    .width(97.dp)
                    .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
                contentScale = ContentScale.FillBounds, // 이미지 비율 무시하고 크기 맞춤
                        colorFilter = if (isSelected) ColorFilter.tint(SteelBlue.copy(0.8f)) else null // 선택 상태에 따라 색상 필터 적용
            )

            // 원 아래 텍스트
            Text(
                text = userName,
                color = SlateGray, // 텍스트 색상
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 130.dp, top = 28.dp) // 텍스트의 위치 조정
                    .background(Color.Transparent), // 배경을 투명하게 설정
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUserInfo() {
    // 예시 사용자 정보
    UserInfo(
        userName = "John Doe",
        profileImageRes = R.drawable.ic_add_group_avatar_94, // 프로필 이미지 리소스 ID
        isSelected = false, // 선택되지 않은 상태
        onClick = { /* 클릭 시 동작 */ }
    )
}

