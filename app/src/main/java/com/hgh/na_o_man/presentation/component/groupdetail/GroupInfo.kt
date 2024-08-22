package com.hgh.na_o_man.presentation.component.groupdetail

import android.graphics.Paint.Align
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.CommonBtn

@Composable
fun GroupInfo(
    title : String,
    participantCount: Int,
    date: String,
){
    val titleLength = title.length + 8
    val textPadding = title.length + 2
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0x00FFFFFF),
            Color(0xCCFFFFFF),
            Color(0x33FFFFFF),
            Color(0xB3FFFFFF),
        ),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    Surface(
        color = Color(0x66FFFFFF),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .size((titleLength * 10).dp, 60.dp)
            .border(2.dp, gradient, RoundedCornerShape(18.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Column 내의 모든 항목을 가운데 정렬
            modifier = Modifier
                .offset(y = 7.dp)
                .fillMaxSize() // Column이 Surface를 가득 채우도록 설정
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.Start)
                    .offset(x = (textPadding).dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, // Row 내의 아이템들을 세로로 가운데 정렬
                modifier = Modifier
                    .align(Alignment.Start)
                    .offset(x = (textPadding).dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_person_13), contentDescription = null, tint = Color.White, modifier = Modifier.size(11.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$participantCount", color = Color.White, fontSize = 14.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date, color = Color.White, fontSize = 14.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun PreviewGroupInfo() {
    GroupInfo("2024 졸업 전시", 5, "2024.04.16")
}