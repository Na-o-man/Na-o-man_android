package com.hgh.na_o_man.presentation.component.homeIcon

import android.media.metrics.Event
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.samplecompose.R

@Composable
fun EventCard(
    imageRes: Int,
    title: String,
    participantCount: Int,
    date: String
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30))
            .background(Color(0xFFE8F0FE), shape = RoundedCornerShape(30.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .offset(x = (20).dp, y = (-20).dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.White, shape = CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(50.dp))
        Column {
            Text(text = title, fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_person_13), contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$participantCount", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun EventListScreen() {
    Column {
        EventCard(
            imageRes = R.drawable.ic_launcher_foreground,
            title = "2024 졸업 전시",
            participantCount = 6,
            date = "2024.04.16"
        )
        EventCard(
            imageRes = R.drawable.ic_launcher_foreground,
            title = "보라카이 여행",
            participantCount = 5,
            date = "2024.04.16"
        )
        EventCard(
            imageRes = R.drawable.ic_launcher_foreground,
            title = "제주도",
            participantCount = 2,
            date = "2024.04.16"
        )
        EventCard(
            imageRes = R.drawable.ic_cloud_138,
            title = "제주도 에코랜드",
            participantCount = 6,
            date = "2024.04.16"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EventCardPreview() {
    EventListScreen()
}