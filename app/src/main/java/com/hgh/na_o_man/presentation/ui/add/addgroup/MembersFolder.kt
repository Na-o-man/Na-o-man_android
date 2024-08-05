package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun MembersFolder(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Log.d("리컴포저블", "MembersSpace")

    val context = LocalContext.current
    val state by viewModel.viewState.collectAsState()
    val photoList = remember { mutableStateListOf("") } // 복사할 이미지 리소스 목록
    val copiedPhotos = remember { mutableStateListOf<Int>() } // 복사된 이미지 목록

    Scaffold(
        containerColor = lightSkyBlue // 여기를 수정
    ) { padding ->
        //구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            StartTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_share_folder_144), // 첫 번째 이미지 리소스
                contentDescription = null,
                modifier = Modifier
                    .padding(1.dp)
                    .offset(y = -(20.dp))
                    .size(200.dp) // 원하는 크기로 설정
            )
            Text(
                text = "제주도 2024", // 텍스트 내용
                color = DeepBlue, // 텍스트 색상
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 23.sp, // 텍스트 크기 설정
                    fontWeight = FontWeight.Bold // 텍스트를 Semibold로 설정
                ),
                modifier = Modifier
                    .offset(y = -(10.dp)) // 텍스트를 위로 올리기 위한 offset 추가
            )

            // 두 번째 이미지 (겹쳐서 놓기)
            Box(
                modifier = Modifier.size(360.dp)
                    .clickable {
                        // 구름 버튼 클릭 시 복사 이벤트
                        copiedPhotos.clear() // 이전 복사 목록 초기화
                        //copiedPhotos.addAll(photoList) // 모든 사진 복사
                        viewModel.onTextInput("사진 복사 이벤트 발생") // 이벤트 전송
                        Toast.makeText(context, "사진이 복사되었습니다.", Toast.LENGTH_SHORT).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_button_cloud_next_140), // 두 번째 이미지 리소스
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .size(100.dp)
                        .offset(x = 80.dp, y = 55.dp)
                )
            }

            // 세 번째와 네 번째 이미지 (아래에 배치)
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .size(350.dp), // 내용을 감싸도록 설정
                contentAlignment = Alignment.Center
            ) {
                // 세 번째와 네 번째 이미지 (아래에 배치)
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .size(350.dp), // 내용을 감싸도록 설정
                    contentAlignment = Alignment.Center
                ) {
                    // 세 번째 이미지와 텍스트
                    Box(
                        modifier = Modifier
                            .width(220.dp)
                            .height(43.dp).offset(y = 135.dp)
                            .clip(RoundedCornerShape(50.dp)) // 둥근 모서리 설정
                            .background(LightWhite), // 배경을 LightWhite 40%로 설정
                        contentAlignment = Alignment.Center // 텍스트를 중앙에 배치
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 세 번째 이미지 리소스
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(), // 버튼 크기에 맞추기
                            contentScale = ContentScale.FillBounds // 비율 무시하고 크기 조정
                        )
                        Text(
                            text = "링크 공유해서 친구 초대하기", // 텍스트 내용
                            color = DeepBlue, // 텍스트 색상
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp, // 텍스트 크기 설정
                                fontWeight = FontWeight.SemiBold // 텍스트를 Semibold로 설정
                            )
                        )
                    }

                    // 네 번째 이미지와 텍스트
                    Box(
                        modifier = Modifier
                            .width(220.dp)
                            .height(43.dp).offset(y = 190.dp)
                            .clip(RoundedCornerShape(50.dp)) // 둥근 모서리 설정
                            .background(LightWhite), // 배경을 LightWhite 40%로 설정
                        contentAlignment = Alignment.Center // 텍스트를 중앙에 배치
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151), // 네 번째 이미지 리소스
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(), // 버튼 크기에 맞추기
                            contentScale = ContentScale.FillBounds // 비율 무시하고 크기 조정
                        )
                        Text(
                            text = "공유 폴더 가기", // 텍스트 내용
                            color = DeepBlue, // 텍스트 색상
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp, // 텍스트 크기 설정
                                fontWeight = FontWeight.SemiBold // 텍스트를 Semibold로 설정
                            )
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFolder() {
    // NavController 생성
    val navController = NavHostController(context = LocalContext.current)
    MembersFolder(navController = navController)
}



