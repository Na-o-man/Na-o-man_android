package com.hgh.na_o_man.presentation.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.theme.SteelBlue

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    image: Dummy,
    isSelectMode: Boolean,
    onClick: (Dummy) -> Unit = {},
    onSelect: (Dummy) -> Unit = {},
) {
    SideEffect {
        Log.d("리컴포저블", "아이디 ${image.id} ")
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        border = if (image.is1) {
            BorderStroke(4.dp, Color.Yellow)
        } else {
            BorderStroke(2.dp, SteelBlue)
        },
        modifier = modifier
            .clickable {
                if (isSelectMode) {
                    onSelect(image)
                } else {
                    onClick(image)
                }
            }
    ) {
        Box {
            AsyncImage(
                model = image.dummyString,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )

            if (image.is2) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Gray.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Composable
fun UriImageCard(
    modifier: Modifier = Modifier,
    imageUri: Uri,
    onClick: (Dummy) -> Unit = {},
) {
    SideEffect {
        Log.d("리컴포저블", " ${imageUri} ")
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, SteelBlue),
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun ImageCardWithProfile(
    modifier: Modifier = Modifier,
    image: Dummy,
    profiles: List<Dummy>,
    isSelectMode: Boolean,
    isVoteMode: Boolean = false,
    isVote: Boolean = false,
    myProfile: Dummy = Dummy(),
    agendaText: String = "",
    onClick: (Dummy) -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 18.dp)
    ) {
        ImageCard(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.25f),
            image = image,
            isSelectMode = isSelectMode,
            onClick = {
                onClick(it)
            },
            onSelect = {}
        )
        if (isVoteMode) {
            if (isVote) {
                PeopleAgenda(
                    profile = myProfile, text = agendaText, modifier = Modifier
                        .align(
                            Alignment.BottomStart
                        )
                        .offset(y = (14).dp)
                        .clickable {
                            onProfileClick()
                        }
                )
            }
        } else {
            PeopleCountCircle(
                member = profiles,
                modifier = Modifier
                    .align(
                        Alignment.BottomStart
                    )
                    .offset(y = (14).dp)
            )
        }
    }
}

@Composable
@Preview
fun preView() {
    ImageCard(
        image = Dummy(dummyString = ""),
        isSelectMode = false
    ) {

    }
}

@Composable
@Preview
fun preView2() {
    ImageCardWithProfile(
        modifier = Modifier.fillMaxWidth(),
        image = Dummy(dummyString = ""),
        profiles = listOf(Dummy(), Dummy()),
        isSelectMode = false,
        isVoteMode = true,
        isVote = true
    ) {

    }
}