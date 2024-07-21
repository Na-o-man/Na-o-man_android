package com.hgh.na_o_man.presentation.component.groupdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.R

@Composable
fun Bigfolder(){
    Box(
        modifier = Modifier.size(293.dp, 260.dp) // 폴더 크기에 맞게 Box 크기 설정

    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_head_122),
            contentDescription = ("폴더머리"),
            tint = Color.White,
            modifier = Modifier
                .offset(x = 2.dp)

        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_head_122),
            contentDescription = ("폴더머리"),
            tint = Color.Unspecified, // 아이콘 색상 설정
                modifier = Modifier
                .offset(x = 2.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_body_293),
            contentDescription = ("폴더몸통"),
            tint = Color.White,
            modifier = Modifier
                .offset(y = (31).dp)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_group_detail_folder_body_293),
            contentDescription = ("폴더몸통"),
            tint = Color.Unspecified,
            modifier = Modifier
                .offset(y = (31).dp)
        )
    }

}


//@Preview(showBackground = true)
//@Composable
//fun PreviewFolder() {
//    Bigfolder()
//}