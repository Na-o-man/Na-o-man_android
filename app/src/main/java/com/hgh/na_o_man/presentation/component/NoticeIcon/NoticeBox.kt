package com.hgh.na_o_man.presentation.component.NoticeIcon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun NoticeBox(
    title: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_notice_yellow_box_148),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 20.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewNoticeBox(){
    NoticeBox(title = "알림")
}
