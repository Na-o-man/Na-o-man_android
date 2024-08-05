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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.Typography

@Composable
fun CommonDialog(
    title: String = "",
    onCancelButtonClick: () -> Unit,
    onClickPositive: () -> Unit,
) {
    Dialog(onDismissRequest = { onCancelButtonClick() }) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            shape = RoundedCornerShape(24.dp),
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
            color = Color(0xCCFFFFFF),
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 40.dp,
                    vertical = 30.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )


                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .height(40.dp)
                            .weight(1f)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .border(
                                2.dp, Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x00FFFFFF),
                                        Color(0xCCFFFFFF),
                                        Color(0x33FFFFFF),
                                        Color(0xB3FFFFFF),
                                    ),
                                    start = Offset.Zero,
                                    end = Offset.Infinite,
                                ), RoundedCornerShape(18.dp)
                            )
                            .clickable {
                                onClickPositive()
                            }
                    ) {
                        Text(
                            text = "예", fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF000000)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .height(40.dp)
                            .weight(1f)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .border(
                                2.dp, Brush.linearGradient(
                                    colors = listOf(
                                        Color(0x00FFFFFF),
                                        Color(0xCCFFFFFF),
                                        Color(0x33FFFFFF),
                                        Color(0xB3FFFFFF),
                                    ),
                                    start = Offset.Zero,
                                    end = Offset.Infinite,
                                ), RoundedCornerShape(18.dp)
                            )
                            .clickable {
                                onCancelButtonClick()
                            }
                    ) {
                        Text(
                            text = "아니요", fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFFF5959)
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun commonDialogPreView() {
    CommonDialog("탈퇴하기겠습니까? \n탈퇴 시 데이터 복구는 불가능합니다", {}, {})
}