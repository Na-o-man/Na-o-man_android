package com.hgh.na_o_man.presentation.component.NoticeIcon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun AlarmButton(
    title: String,
    onClick: () -> Unit = {}
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable ( onClick = onClick )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_notice_rec_63),
            contentDescription = "알림페이지 버튼",
            tint = Color.Unspecified
        )
        Text(
            text = title,
            style = com.hgh.na_o_man.presentation.theme.Typography.labelMedium,
            fontSize = 11.sp
        )
    }
}

@Composable
fun ReadAllUnClickableButton(
    title: String,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_notice_rec_2_63),
            contentDescription = "알림페이지 버튼",
            tint = Color.Unspecified
        )
        Text(
            text = title,
            style = com.hgh.na_o_man.presentation.theme.Typography.labelMedium,
            fontSize = 11.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun PreviewNoticeButton(){
    ReadAllUnClickableButton("모두 읽음")
}