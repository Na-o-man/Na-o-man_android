package com.hgh.na_o_man.presentation.component.groupdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgh.na_o_man.R

@Composable
fun GroupInfo(
    title : String,
    participantCount: Int,
    date: String
){
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
            contentDescription = "Group Info",
            tint = Color.Unspecified
        )

        Column {
            Text(text = title, fontSize = 15.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_person_13), contentDescription = null, tint = Color.White, modifier = Modifier.size(11.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "$participantCount", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date, color = Color.White, fontSize = 14.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupInfo() {
    GroupInfo("2024 졸업 전시", 5, "2024.04.16")
}