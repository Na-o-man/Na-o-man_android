package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hgh.na_o_man.R

@Composable
fun EventCard(
    imageRes: String,
    title: String,
    participantCount: Int,
    date: String,
    groupId: Long = 33L,
    onClick: (Long) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(top = 20.dp)
            .clickable(onClick = { onClick(groupId) }) // 클릭 이벤트 추가
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_group_box_button_299),
            contentDescription = "그룹 리스트",
            tint = Color(0xAA8D8D8D),
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_group_box_button_299),
            contentDescription = "그룹 리스트",
            tint = Color.Unspecified,
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 160.dp, end = 20.dp) // 끝쪽 패딩 추가
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.widthIn(max = 150.dp) // 텍스트의 최대 너비 설정
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person_13),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$participantCount", color = Color.White, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(17.dp))
                Text(text = date, color = Color.White, fontSize = 16.sp)
            }
        }

        Box(
            modifier = Modifier
                .offset(x = -80.dp, y = (-15).dp)
                .clickable(onClick = { onClick(groupId) }) // 클릭 이벤트 추가
                .zIndex(1f) // 이미지가 다른 요소들 위에 렌더링되도록 설정
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    Column (){
        EventCard(imageRes = R.drawable.ic_example.toString(), title = "제목", participantCount = 5, date = "2024.07.20")
        EventCard(imageRes = R.drawable.ic_example.toString(), title = "제목asdfasdfasdfasf", participantCount = 5, date = "2024.07.20")
    }
}