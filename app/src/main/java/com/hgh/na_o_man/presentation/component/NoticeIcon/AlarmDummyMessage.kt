package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.rememberAsyncImagePainter
import com.hgh.na_o_man.R

@Composable
fun AlarmDummyRead(
    imageRes: Int,
    detail: String,
    date: String,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick) // 클릭 이벤트 추가
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_notice_read_312),
            contentDescription = "그룹 리스트",
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 50.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier
                .weight(1f)
            ) {
                Text(
                    text = detail,
                    lineHeight = 16.sp,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier
                        .widthIn(max = 210.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    fontSize = 9.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Composable
fun AlarmDummyNotRead(
    imageRes: Int,
    detail: String,
    date: String,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick) // 클릭 이벤트 추가
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_notice_not_read),
            contentDescription = null,
            modifier = Modifier
                .requiredSize(310.dp, 85.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 50.dp, top = 10.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column (
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = detail,
                    lineHeight = 16.sp,
                    fontSize = 11.sp,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier
                        .widthIn(max = 210.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    fontSize = 9.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmDummyMessagePreview() {
    Column {
        AlarmDummyRead(
            imageRes = R.drawable.ic_example,
            detail = "[개구쟁어로즈]ㄹㅁㄴㅇㄹㄴㅇㅁㄹㅁdfdffdfdfdfdfdfdfdfdfdfdffdfdffdfdfㄴㅇㄹㅁㄴㄹㄴㅁㄹㅁㄴㄹㅇㅁㄴㄹㄴㅁㅇㄹㅁㄴㅇㄹㅁㄴㅇ해변히어럼ㄴ에했습니다.",
            date = "2024.07.20"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AlarmDummyNotRead(
            imageRes = R.drawable.ic_example,
            detail = "[개구쟁이 친구들과 발리 한마당 : 해변 히어로즈]에 김봉순 님이 n장 사진을 업로드 했습니다.",
            date = "2024.07.21"
        )
    }
}