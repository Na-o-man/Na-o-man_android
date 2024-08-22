package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.domain.model.share_group.ProfileInfoModel

@Composable
fun PeopleCountCircle(
    member: List<ProfileInfoModel>,
    maxSize: Int = 3,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.wrapContentSize()) {
        Row(
            modifier = Modifier.wrapContentSize()
        ) {
            member.take(maxSize).forEachIndexed { index, profile ->
                Box(
                    modifier = Modifier
                        .offset(x = (-10).dp * index)
                ) {
                    CircleImage(imageUrl = profile.image)
                }
            }

            if (member.size > maxSize) {
                Box(
                    modifier = Modifier
                        .offset(x = (-10).dp * maxSize)
                ) {
                    CircleText("+${member.size - maxSize}")
                }
            }
        }
    }
}

@Composable
fun PeopleAgenda(
    profile: String = "",
    text: String = "",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
    ) {
        if (text.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1D3A72),
                    fontSize = 12.sp,
                    maxLines = 1,
                )
            }
        }
        AsyncImage(
            model = profile,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CircleImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .border(1.dp, Color.White, CircleShape)
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
            .background(Color(0xFF8BA5C1)),
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
    PeopleCountCircle(listOf())
}

@Preview
@Composable
fun angPreview() {
    PeopleAgenda(profile = "")
}