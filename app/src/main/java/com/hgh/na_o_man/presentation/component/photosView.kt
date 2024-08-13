package com.hgh.na_o_man.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel

@Composable
fun AgendaPhotos(
    images: List<PhotoInfoModel>,
    voteId: Long = 33L,
    onClick: (Long) -> Unit = {}
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

    Box(
        modifier = Modifier
            .background(Color(0xBFFFFFFF), shape = RoundedCornerShape(14.dp))
            .border(1.dp, gradient, RoundedCornerShape(14.dp))
            .clickable(onClick = { onClick(voteId) })
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {


            Log.d("getVoteList", "Middle check")

            images.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    rowItems.forEach { item ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1.32f)
                                .padding(6.dp)
                        ) {
                            ImageCard(
                                image = item,
                                isSelectMode = false
                            )
                        }
                        if (rowItems.size < 2) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(6.dp)
                            )
                        }
                    }
                }
            }

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31),
                contentDescription = "Center Image",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun PreviewVoteList() {
    AgendaPhotos(images = listOf(PhotoInfoModel(), PhotoInfoModel()))
}