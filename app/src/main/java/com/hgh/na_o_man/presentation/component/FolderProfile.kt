package com.hgh.na_o_man.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.theme.Typography

@Composable
fun FolderProfile(
    folderInfo : Dummy,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = folderInfo.dummyString,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_add_group_avatar_94),
            modifier = modifier
                .clip(CircleShape)
                .border(width = 3.dp, color = Color.White, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = folderInfo.dummyString3, color = Color.Black, fontSize = 20.sp,)
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun FolderPreview() {
    FolderProfile(Dummy(), Modifier.size(200.dp))
}
