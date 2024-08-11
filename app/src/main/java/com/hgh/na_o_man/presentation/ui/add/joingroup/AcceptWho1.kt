package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.userIcon.UserInfo
import com.hgh.na_o_man.presentation.theme.lightSkyBlue

@Composable
fun AcceptWho1(
    navController: NavHostController,
    onProfileSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(360.dp, 350.dp)
            .background(Color.Transparent)
    ) {
        Scaffold(
            containerColor = lightSkyBlue
        ) { padding ->
            // 첫 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 8.dp)
                    .background(Color.Transparent)
            ) {
                UserInfo(
                    userName = "홍길동",
                    profileImageRes = R.drawable.ic_add_group_avatar_94, // 프로필 이미지 리소스 추가
                    onClick = { onProfileSelected("홍길동") } // 선택된 프로필을 onProfileSelected로 전달
                )
            }

            // 두 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 110.dp, bottom = 8.dp)
                    .background(Color.Transparent)
            ) {
                UserInfo(
                    userName = "홍길은",
                    profileImageRes = R.drawable.ic_add_group_avatar_94, // 프로필 이미지 리소스 추가
                    onClick = { onProfileSelected("홍길은") } // 선택된 프로필을 onProfileSelected로 전달
                )
            }

            // 세 번째 UserInfo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 230.dp)
                    .background(Color.Transparent)
            ) {
                UserInfo(
                    userName = "홍길금",
                    profileImageRes = R.drawable.ic_add_group_avatar_94, // 프로필 이미지 리소스 추가
                    onClick = { onProfileSelected("홍길금") } // 선택된 프로필을 onProfileSelected로 전달
                )
            }
        }
    }
}

