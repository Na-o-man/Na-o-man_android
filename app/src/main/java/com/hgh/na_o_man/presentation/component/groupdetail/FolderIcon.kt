package com.hgh.na_o_man.presentation.component.groupdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.FolderDummy
import com.hgh.na_o_man.presentation.component.FolderProfile

@Composable
fun Bigfolder(
    folderInfo: FolderDummy? = null, // FolderProfile에 필요한 정보를 전달하기 위한 매개변수
    onClick :() -> Unit = {}
){
    Row(
        modifier = Modifier
            .padding(top = 44.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .weight(1f)
            .background(Color(0xFFBBCFE5))){
            Text(text = "", modifier = Modifier.fillMaxSize())
        }
        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
        ) {
            Box(modifier = Modifier.alpha(0.6f)){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_folder),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            folderInfo?.let {
                FolderProfile(
                    folderInfo = it,
                    modifier = Modifier
                        .offset(y = 20.dp)
                        .align(Alignment.Center)
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                contentDescription = "낭만 로고",
                tint = Color.Unspecified,
                modifier = Modifier
                    .offset(x = 210.dp,y = 45.dp)
            )
        }
        Box(modifier = Modifier
            .weight(1f)
            .background(Color(0xFFBBCFE5))){
            Text(text = "", modifier = Modifier.fillMaxSize())
        }
    }

}

@Composable
fun SmallFolder(page : Int){
    Box(
        modifier = Modifier
    ) {
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
                .offset(x = 240.dp, y = 50.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFolder() {
//    SmallFolder(2)
    Bigfolder()
}