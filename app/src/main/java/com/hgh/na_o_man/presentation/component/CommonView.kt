package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun CommonTitle(
    title: String,
    maxLine: Int = 1,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
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
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .border(2.dp, gradient, RoundedCornerShape(14.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .padding(vertical = 16.dp, horizontal = 16.dp),

            ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .rotate(30f)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF1D3A72),
                modifier = Modifier
                    .padding(start = 16.dp, end = 12.dp)
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLine,
            )
        }
    }
}

@Preview
@Composable
fun CommonPreview() {
    CommonTitle(title = "사진 추가하기,추가하기추가하기추가하기추가하기추가하기추가하기추가하기추가하기추가하기추가하기", maxLine = 2) {}
}