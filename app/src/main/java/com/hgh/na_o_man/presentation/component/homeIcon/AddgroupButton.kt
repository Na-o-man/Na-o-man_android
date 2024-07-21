package com.hgh.na_o_man.presentation.component.homeIcon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun AddGroupButton(
    title : String,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_add_group_166),
            contentDescription = "add group button",
            tint = Color(0xAA8D8D8D),
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_add_group_166),
            contentDescription = "add group button",
            tint = Color.Unspecified,
        )
        Text(
            text = title,
            color = Color(0xFF748292),
            style = com.hgh.na_o_man.presentation.theme.Typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddGroupButton() {
    AddGroupButton("그룹 추가")
}