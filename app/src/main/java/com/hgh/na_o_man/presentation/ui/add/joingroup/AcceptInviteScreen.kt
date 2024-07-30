package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.runtime.Composable
import android.util.Log
import android.webkit.URLUtil.isValidUrl
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.NextAppBar2
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel


@Composable
fun AcceptInviteScreen(
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    val viewState by viewModel.viewState.collectAsState()
    Log.d("리컴포저블", "MembersSpace")
    var url by remember { mutableStateOf("URL을 입력해 주세요.") }

//    when (viewState.loadState) {
//        LoadState.LOADING -> {
//            StateLoadingScreen()
//        }
//
//        LoadState.ERROR -> {
//            StateErrorScreen()
//        }
//
//        LoadState.SUCCESS -> {
    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { }
            )
        },
        containerColor = lightSkyBlue // 여기를 수정
    ) { padding ->
        //구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 15.dp, end = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 왼쪽 이미지 (30도 회전)
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .height(19.dp)
                        .width(15.3.dp)// 이미지 크기 조정 (필요에 따라 조정)
                        .graphicsLayer(rotationZ = -120f) // 30도 회전
                )

                // 텍스트
                Text(
                    text = "그룹 링크를 만들어주세요.",
                    modifier = Modifier.padding(start = 16.dp), // 이미지와 텍스트 사이의 간격
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // 화면 중앙 하단 이미지
            Box(
                modifier = Modifier
                    .padding(top = 90.dp)
                    .size(width = 295.dp, height = 55.dp)
                    .background(
                        color = LightWhite.copy(alpha = 0.7f), // 투명도 0.8로 설정
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = LightWhite, shape = RoundedCornerShape(20.dp)
                    )
            ) {
                // 이미지 위에 텍스트
                var isFocused by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .padding(start = 90.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    BasicTextField(
                        value = url,
                        onValueChange = { url = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .onFocusChanged { state ->
                                isFocused = state.isFocused
                            },
                        textStyle = TextStyle(
                            color = SteelBlue,
                            background = Color.Transparent,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        cursorBrush = SolidColor(SteelBlue),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }
            }


            val context = LocalContext.current // 현재의 컨텍스트 가져오기

            //이미지 배경을 화면 중앙 오른쪽에 추가
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(top = 215.dp, end = 40.dp)
            ) {
                NextAppBar1(
                    onStartClick = { },
                    onEndClick = { },
                    onNextClick = {
                        // URL 검증 로직
                        if (isValidUrl(url)) {
                            viewModel.handleEvents(AddContract.AddEvent.AddGroup6) // 다음 스크린으로 이동
                        } else {
                            // URL이 유효하지 않을 때 사용자에게 알림
                            Toast.makeText(context, "유효한 URL을 입력하세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            // URL 유효성 검사 함수
            fun isValidUrl(url: String): Boolean {
                // 정규 표현식을 사용하여 URL 검증 (예시)
                val urlPattern = "^(https?://)?(www\\.)?([\\w-]+\\.)+[\\w-]+(/\\S*)?$".toRegex()
                return urlPattern.matches(url)
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun Preview7() {
//    // NavController 생성
//    val navController = rememberNavController()
//    // AddViewModel의 더미 인스턴스 생성
//    val dummyViewModel = object : AddViewModel(navController) {
//        // 필요한 프로퍼티나 메소드 오버라이드
//    }
//    // AcceptInviteScreen에 navController와 dummyViewModel 전달 -> viewModel
//    AcceptInviteScreen(viewModel = dummyViewModel)
//}

