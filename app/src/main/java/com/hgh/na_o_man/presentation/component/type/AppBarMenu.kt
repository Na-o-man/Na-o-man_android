package com.hgh.na_o_man.presentation.component.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R

enum class AppBarMenu(
    val horizontalPadding: Dp,
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
}
