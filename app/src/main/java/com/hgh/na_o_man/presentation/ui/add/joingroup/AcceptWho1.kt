package com.hgh.na_o_man.presentation.ui.add.joingroup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    members: List<Member>, // 변경된 부분: List<Member>를 받도록 수정
    selectedProfile: Member?, // 선택된 프로필 이름
    currentPage: Int // 현재 페이지 번호 추가
) {
    val firstItemIndex = currentPage * 3
    val firstItemIsSelectable = firstItemIndex < members.size &&
            (selectedProfile == null || members[firstItemIndex].name != selectedProfile!!.name)

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
                    val isFirstItem = index == firstItemIndex
                    val isSelectable = !(isFirstItem && !firstItemIsSelectable)

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Transparent)
                    ) {
                        val painter = if (member.avatarUrl != null) {
                            rememberAsyncImagePainter(member.avatarUrl)
                        } else {
                            painterResource(id = R.drawable.ic_add_group_avatar_94)
                        }

                        Image(
                            painter = painter,
                            contentDescription = "Avatar ${member.name}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }

                    UserInfo(
                        userName = member.name,
                        isSelected = member.name == selectedProfile?.name && isSelectable,
                        profileImageRes = R.drawable.ic_add_group_avatar_94, // 이 부분은 필요하지 않음
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


