package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy

@Composable
fun VoteBeforeDialog(
    image: Dummy,
    onCancelButtonClick: () -> Unit,
    onVoteClick: (String, Long) -> Unit,
) {
    var text = remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest = { onCancelButtonClick() }) {

        Box{

            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box{
                    ImageCard(
                        image = image, isSelectMode = false, modifier = Modifier
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
                                onVoteClick(text.value.text, image.id.toLong())
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
    image: Dummy,
    onCancelButtonClick: () -> Unit,
    onVoteClick: (String) -> Unit,
) {
    var text = remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest = { onCancelButtonClick() }) {

        Box(
            Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Box {
                    ImageCard(
                        image = image, isSelectMode = false, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .height(160.dp)
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
                                onVoteClick(text.value.text)
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
@Preview
fun AfterPreView() {
    VoteBeforeDialog(Dummy(), {}) { _, _ ->

    }
}