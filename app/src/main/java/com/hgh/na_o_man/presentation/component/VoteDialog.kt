package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.photo.PhotoInfoModel
import com.hgh.na_o_man.domain.model.vote.VoteDetailModel
import com.hgh.na_o_man.domain.model.vote.VoteInfoModel
import com.hgh.na_o_man.presentation.theme.LightWhite

@Composable
fun VoteBeforeDialog(
    voteData: VoteDetailModel,
    onCancelButtonClick: () -> Unit,
    onVoteClick: (String, Long) -> Unit,
) {
    var text = remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest = { onCancelButtonClick() }) {

        Box {

            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    ImageCard(
                        // 포토수정필요
                        image = voteData.photoInfo,
                        isSelectMode = false,
                        modifier = Modifier
                            .wrapContentHeight()
                            .shadow(3.dp, RoundedCornerShape(16.dp))
                    )

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd), onClick = onCancelButtonClick
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_close_26),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 50.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                        .background(Color(0xCCFFFFFF), shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = text.value,
                        onValueChange = {
                            if (it.text.length <= 20) {
                                text.value = it
                            }
                        },
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .background(Color.Transparent)
                                    .padding(4.dp)// Adjust padding as needed
                            ) {
                                if (text.value.text.isEmpty()) {
                                    Text(text = "의견을 남겨주세요", color = Color.Gray)
                                }
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                    )
                    Row(
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(6.dp))
                            .background(color = Color(0xCCFFFFFF), shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                            .clickable {
                                onVoteClick(text.value.text, voteData.agendaPhotoId)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "투표 하기",
                            color = Color(0xFF515151),
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow_15), // 아이콘 리소스를 사용해야 합니다.
                            contentDescription = "arrow",
                            tint = Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun VoteAfterDialog(
    voteData: VoteDetailModel,
    title: String,
    onCancelButtonClick: () -> Unit = {},
) {
    Dialog(onDismissRequest = { onCancelButtonClick() }) {
        Box {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    2.dp, Brush.linearGradient(
                        colors = listOf(
                            Color(0x00FFFFFF),
                            Color(0xCCFFFFFF),
                            Color(0x33FFFFFF),
                            Color(0xB3FFFFFF),
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    )
                ),
                color = LightWhite.copy(alpha = 0.8f, blue = 50f)
            ) {
                Column {
                    CommonTitle(
                        title = title, modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 16.dp)
                    ) {

                    }
                    Box(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        ImageCard(
                            image = voteData.photoInfo,
                            isSelectMode = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .shadow(3.dp, RoundedCornerShape(16.dp))
                        )

                        IconButton(
                            modifier = Modifier
                                .align(Alignment.TopEnd), onClick = onCancelButtonClick
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_close_26),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                    PeopleCountCircle(
                        member = voteData.voteInfoList.map {
                            it.profileInfo
                        }, maxSize = 10,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 240.dp)
                            .padding(horizontal = 16.dp)
                            .padding(top = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(voteData.voteInfoList, key = { _, it ->
                            it.voteId
                        }) { _, vote ->

                            val isLastItem = voteData.voteInfoList.last() == vote
                            val modifier = if (isLastItem) {
                                Modifier.padding(bottom = 16.dp)
                            } else {
                                Modifier.padding(bottom = 2.dp)
                            }

                            OpinionItem(vote, modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpinionItem(
    voteInfo: VoteInfoModel,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(18.dp),
        modifier = modifier
            .heightIn(min = 42.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            2.dp, Brush.linearGradient(
                colors = listOf(
                    Color(0x00FFFFFF),
                    Color(0xCCFFFFFF),
                    Color(0x33FFFFFF),
                    Color(0xB3FFFFFF),
                ),
                start = Offset.Zero,
                end = Offset.Infinite,
            )
        ),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = voteInfo.comment,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1D3A72),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            )
            Text(
                text = voteInfo.profileInfo.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            CircleImage(
                imageUrl = voteInfo.profileInfo.image,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
    }
}


@Preview
@Composable
fun AfterPreView() {
//    VoteBeforeDialog(Dummy(), {}) { _, _ ->
//
//    }
}

@Composable
@Preview
fun ItemPreView() {
    //OpinionItem(Dummy())
}

@Composable
@Preview
fun BeforePreView() {
    // VoteAfterDialog(Dummy(), listOf(Dummy(), Dummy(), Dummy(), Dummy()), {})
}
