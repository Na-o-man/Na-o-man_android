package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hgh.na_o_man.presentation.component.userIcon.UserInfo
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel


@Composable
fun AcceptWho2(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel(),
    showBackIcon: Boolean = false, // 아이콘을 보여줄지 여부를 받는 매개변수
) {
    Log.d("리컴포저블", "AcceptWho2")

    // 전체 화면 크기를 줄이기 위한 Box 추가
    Box(
        modifier = Modifier
            .size(360.dp, 350.dp) // 원하는 크기로 설정 (너비, 높이)
            .background(Color.Transparent)
    ) {
        Scaffold(
            containerColor = lightSkyBlue
        ) { padding ->
            // 첫 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -15.dp)
                    .padding(bottom = 8.dp) // 아래쪽에 간격 추가
                    .background(Color.Transparent) // 배경을 투명하게 설정
            ) {
                UserInfo()
            }

            // 두 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -15.dp, y = 110.dp)
                    .padding(bottom = 8.dp) // 아래쪽에 간격 추가
                    .background(Color.Transparent) // 배경을 투명하게 설정
            ) {
                UserInfo()
            }

            // 세 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = -15.dp, y = 220.dp)
                    .background(Color.Transparent) // 배경을 투명하게 설정
            ) {
                UserInfo()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview11() {
    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
    AcceptWho2(navController = navController)
}