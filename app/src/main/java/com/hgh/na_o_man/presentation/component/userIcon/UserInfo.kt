package com.hgh.na_o_man.presentation.component.userIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun UserInfo() {
    // 전체 크기를 줄이기 위한 Box
    Box(
        modifier = Modifier
            .size(355.dp, 130.dp) // 원하는 크기로 조정
            .padding(16.dp) // 여백 추가
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

        // 원 1
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_group_avatar_94),
                contentDescription = "Profile",
                modifier = Modifier
                    .background(Color.Transparent) // 배경을 투명하게 설정
                    .padding(start = 20.dp)
                    .height(97.dp)
                    .width(97.dp),
                colorFilter = ColorFilter.tint(DeepBlue)
            )

            // 원 아래 텍스트
            Text(
                text = "홍길금",
                color = SlateGray, // 텍스트 색상
                modifier = Modifier
                    .offset(y = 28.dp)
                    .background(Color.Transparent) // 배경을 투명하게 설정
                    .padding(start = 130.dp), // 원과의 간격
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoPreview() {
    UserInfo() // UserInfo 컴포저블을 호출하여 프리뷰
}
