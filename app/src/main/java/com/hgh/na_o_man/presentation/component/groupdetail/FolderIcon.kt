package com.hgh.na_o_man.presentation.component.groupdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.presentation.component.DecorationCloud
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.samplecompose.R

@Composable
fun Bigfolder(){
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_head_122),
        contentDescription = ("폴더머리"),
        tint = Color.Gray,
        modifier = Modifier
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30))
    )
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_body_293),
        contentDescription = ("폴더몸통"),
        tint = Color.Gray,
        modifier = Modifier
            .offset(y = (31).dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(12))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFolder() {
    Bigfolder()
}

