package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoGroupBox() {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(0x80FFFFFF), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "아직 공유 그룹이 없어요.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "그룹을 추가해 주세요.",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoGroupBox() {
    NoGroupBox()
}