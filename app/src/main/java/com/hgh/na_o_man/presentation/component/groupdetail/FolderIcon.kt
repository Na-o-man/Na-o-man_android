package com.hgh.na_o_man.presentation.component.groupdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.component.FolderProfile

@Composable
fun Bigfolder(){

    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_folder),
        contentDescription = null,
        tint = Color.Unspecified

    )

    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
        contentDescription = "낭만 로고",
        tint = Color.Unspecified,
        modifier = Modifier
            .offset(x = 240.dp, y = 60.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewFolder() {
    Bigfolder()
}