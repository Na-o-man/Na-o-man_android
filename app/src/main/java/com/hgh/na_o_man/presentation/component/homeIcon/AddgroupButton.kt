package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun AddGroupButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* TODO */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBBCFE5)), // 바탕 색상 변경 필요
        shape = RoundedCornerShape(50),
        modifier = modifier
            .padding(8.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(50))
            .background(Color.Transparent)
    ) {
        Text(text = "공유 그룹 추가하기", color = Color.White)  // 글씨 색상 변경하기
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddGroupButton() {
    AddGroupButton()
}