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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.SlateGray

@Composable
fun UserInfo(
    userName: String,
    profileImageRes: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(355.dp, 130.dp) // 기존 크기 유지
            .padding(16.dp) // 여백 추가
            .clickable(onClick = onClick) // 클릭 가능하도록 설정
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
                .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
        )

        // 두 번째 이미지
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_info_151),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(320.dp)
                .padding(start = 50.dp, top = 77.dp) // 여전히 겹치게 설정
                .background(Color.Transparent) // 배경을 투명하게 설정
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
        )

        // 프로필 이미지
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp) // 여백 추가
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = profileImageRes),
                contentDescription = "Profile",
                modifier = Modifier
                    .background(Color.Transparent) // 배경을 투명하게 설정
                    .height(97.dp)
                    .width(97.dp)
                    .clip(RoundedCornerShape(50.dp)), // 둥근 모서리 적용
                colorFilter = ColorFilter.tint(DeepBlue),
                contentScale = ContentScale.FillBounds // 이미지 비율 무시하고 크기 맞춤
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
fun UserInfoPreview() {
    UserInfo(
        userName = "홍길동",
        profileImageRes = R.drawable.ic_add_group_avatar_94,
        onClick = {}
    ) // UserInfo 컴포저블을 호출하여 프리뷰
}