package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CommonBtn(
    title: String,
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
            .size(200.dp, 60.dp)
            .border(2.dp, gradient, RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),

            ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4879AF),
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun SignBtn(
    title: String,
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
            .size(200.dp, 60.dp)
            .border(2.dp, gradient, RoundedCornerShape(18.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),

            ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4879AF),
                modifier = Modifier
                    .padding(start = 32.dp)
                    .weight(1f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun MyPageBtn(
    onClick: () -> Unit,
    text: String,
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0x00FFFFFF),
            Color(0xFFFFFFFF),
        ),
    )

    Surface(
        color = Color(0x66FFFFFF),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(200.dp, 40.dp)
            .border(2.dp, gradient, RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),

            ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFFFFFF),
                modifier = Modifier,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

        }
    }
}

@Preview
@Composable
fun MyPageBtnPreview() {
    MyPageBtn(onClick = {}, text = "로그아웃")
}

@Preview
@Composable
fun SignBtnPreview() {
    SignBtn(title = "사진 추가하기") {}
}

@Preview
@Composable
fun CommonBtnPreview() {
    CommonBtn(title = "사진 추가하기") {}
}