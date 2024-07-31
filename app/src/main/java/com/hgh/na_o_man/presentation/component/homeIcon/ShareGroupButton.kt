package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hgh.na_o_man.R

@Composable
fun ShareGroupButton(
    title: String,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_home_162),
            contentDescription = null,
            tint = Color.Unspecified,

        )
        Text(
            text = title,
            style = com.hgh.na_o_man.presentation.theme.Typography.bodyLarge)
    }
}


//@Preview
//@Composable
//fun PreviewShareGroupButton (){
//    ShareGroupButton()
//}