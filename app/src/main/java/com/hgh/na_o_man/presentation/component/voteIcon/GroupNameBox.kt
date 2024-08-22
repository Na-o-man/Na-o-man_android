package com.hgh.na_o_man.presentation.component.voteIcon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GroupNameBox(
    title: String
) {
    val titleLength = title.length * 8
    Surface(
        modifier = Modifier
            .width((titleLength * 1.5).dp)
            .height(29.dp),
        color = Color.Transparent
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, 0f) // 왼쪽 상단 시작점
                lineTo(width, 0f) // 직사각형의 위쪽 라인
                lineTo(width * 0.90f, height * 0.5f) // 오른쪽 중간 지점 (삼각형의 꼭짓점)
                lineTo(width, height) // 직사각형의 아래쪽 라인
                lineTo(0f, height) // 왼쪽 하단으로 이동
                close() // 경로 닫기
            }
            drawPath(
                path = path,
                color = Color(0xFFFAD768) // 원하는 색상
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupNameBoxPreview() {
    GroupNameBox(title = "dfasdㅇfasasfas")
}
