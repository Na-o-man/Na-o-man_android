package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.userIcon.UserInfo
import com.hgh.na_o_man.presentation.theme.lightSkyBlue

@Composable
fun AcceptWho1(
    navController: NavHostController,
    onProfileSelected: (String) -> Unit,
    members: List<Member>,
    selectedProfile: Member?,
    currentPage: Int
) {
    val firstItemIndex = currentPage * 3
    val firstItemIsSelectable = firstItemIndex < members.size &&
            (selectedProfile == null || members[firstItemIndex].name != selectedProfile.name)

    Box(
        modifier = Modifier
            .size(360.dp, 400.dp)
            .background(Color.Transparent)
    ) {
        Scaffold(
            containerColor = lightSkyBlue
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 8.dp)
                    .background(Color.Transparent)
            ) {
                members.forEachIndexed { index, member ->
                    // 첫 번째 항목은 선택이 불가능하도록 설정
                    val isSelectable = index != firstItemIndex
                    // 만약 avatarUrl이 null이거나 유효하지 않다면 기본 이미지를 사용
                    val painter = if (member.avatarUrl.isNotEmpty()) {
                        rememberAsyncImagePainter(member.avatarUrl) // 사용자 정의 이미지
                    } else {
                        painterResource(id = R.drawable.ic_add_group_avatar_94) // 기본 이미지
                    }

                    UserInfo(
                        userName = member.name,
                        profileImagePainter = painter,
                        isSelected = selectedProfile?.name == member.name && isSelectable,
                        onClick = {
                            if (isSelectable) {
                                onProfileSelected(member.name)
                            }
                        }
                    )
                }
            }
        }
    }
}
