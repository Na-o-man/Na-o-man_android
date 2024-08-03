package com.hgh.na_o_man.presentation.component.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R

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
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_menu_39,
        contentDescription = R.string.my_page_description,
    ),

    CHECK(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_button_check_box_39,
        contentDescription = R.string.checkable,
    ),

    Next1(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_button_next_38,
        contentDescription = R.string.next1_description,
    ),

    Next2(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_button_cloud_next_140,
        contentDescription = R.string.next2_description,
    ),

    Plus(
        horizontalPadding = 20.dp,
        icon = R.drawable.ic_button_nav_plus_new_31,
        contentDescription = R.string.plus_description,
    )
}
