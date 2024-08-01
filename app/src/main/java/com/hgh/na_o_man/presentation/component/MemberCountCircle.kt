package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hgh.na_o_man.domain.model.Dummy

@Composable
fun PeopleCountCircle(
    member: List<Dummy>,
    maxSize : Int = 3,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.wrapContentSize()) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy((-10).dp),
        ) {
            items(maxSize) { index ->
                CircleImage(imageUrl = member[index])
            }

            if (member.size - maxSize > 0) {
                item {
                    CircleText("+${member.size - maxSize}")
                }
            }

        }
    }

}

@Composable
fun CircleImage(imageUrl: Dummy) {
    AsyncImage(
        model = imageUrl.dummyString,
        contentDescription = null,
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CircleText(text: String) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun preview() {
    PeopleCountCircle(listOf(Dummy(), Dummy(), Dummy(), Dummy(),Dummy()))
}