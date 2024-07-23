package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun NoGroupBox(
    message : String,
) {
    Box(
        contentAlignment = Alignment.Center,
        ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_no_group_message_299), // 아이콘 리소스 ID를 사용
            contentDescription = "Box",
            tint = Color(0xAA8D8D8D),
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_no_group_message_299), // 아이콘 리소스 ID를 사용
            contentDescription = "Box",
            tint = Color.Unspecified // 아이콘 색상 설정
        )

        Box(
            modifier = Modifier
        ) {
            Column {
                Text(
                    textAlign = TextAlign.Center,
                    text = message,
                    color = Color.White,
                    style = com.hgh.na_o_man.presentation.theme.Typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(30.dp))
                AddGroupButton("공유 그룹 추가하기")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewNoGroupBox() {
//    NoGroupBox(message = "아직 공유그룹이 없어요.\n그룹을 추가해 주세요.")
//}