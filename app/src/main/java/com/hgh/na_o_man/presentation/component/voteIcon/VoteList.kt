package com.hgh.na_o_man.presentation.component.voteIcon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import coil.compose.rememberAsyncImagePainter

@Composable
fun getVoteList(
    title : String,
    images : List<String>,
    voteId: Long = 33L,
    onClick:(Long) -> Unit = {}
) {
    Log.d("getVoteList", "Rendering getVoteList with title: $title")

    // 이미지 리스트의 개수에 따라 높이 지정
    val gridHeight = when (images.size) {
        in 1..2 -> 130.dp + 16.dp // 이미지 한 줄 크기 + 패딩
        in 3..4 -> 280.dp + 16.dp // 이미지 두 줄 크기 + 패딩
        else -> 420.dp + 16.dp // 이미지 세 줄 크기 + 패딩
    }

    Box (
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .clickable(onClick = { onClick(voteId) })
    ) {
        Column(
           modifier = Modifier
               .padding(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepBlue
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Log.d("getVoteList","Middle check")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(gridHeight)

            ) {
                Log.d("getVoteList","Middle check2")
                items(images) { image ->
                Log.d("getVoteList", "Loading image resource: $image")

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = image,
                            error = painterResource(id = R.drawable.ic_example)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(130.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                    )
                    Log.d("getVoteList","Image loaded successfully")
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewVoteList() {
//    getVoteList(title = "이번 여행을 대표할 엽사는?", images = listOf(R.drawable.ic_example, R.drawable.ic_example,R.drawable.ic_example))
//}