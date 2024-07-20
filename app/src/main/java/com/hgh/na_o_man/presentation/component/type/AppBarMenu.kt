package com.hgh.na_o_man.presentation.component.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgh.samplecompose.R
import java.lang.reflect.Modifier

enum class AppBarMenu(
    val horizontalPadding: Dp,
    val shape: Shape = RectangleShape, // 사각형 모양
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
) {
    BACK(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_button_back_arrow_38,
        contentDescription = R.string.back_description,
    ),

    MYPAGE(
        horizontalPadding = 20.dp,]
        icon = R.drawable.ic_menu_39,
        contentDescription = R.string.my_page_description,
    )

}
