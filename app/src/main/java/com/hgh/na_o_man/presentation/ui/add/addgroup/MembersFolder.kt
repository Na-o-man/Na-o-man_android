package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute


@Composable
fun MembersFolder(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Log.d("리컴포저블", "MembersFolder")

    val context = LocalContext.current
    val state by viewModel.viewState.collectAsState()

    val groupName = state.groupName
    val isGroupCreated = state.isGroupCreated
    val inviteLink = state.inviteLink // 초대 링크 가져오기

    LaunchedEffect(isGroupCreated) {
        if (isGroupCreated) {
            navController.navigate(AddScreenRoute.FOLDER.route)
        }
    }

    Scaffold(
        containerColor = lightSkyBlue // 배경 색상 설정
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            StartTopCloud() // 구름 배경

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .wrapContentSize()
                    .align(Alignment.Center)
            ) {
                // 그룹 이미지
                Image(
                    painter = painterResource(id = R.drawable.ic_share_folder_144),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .offset(y = -(20.dp))
                        .size(200.dp)
                )

                // 그룹 이름 텍스트
                Text(
                    text = groupName,
                    color = DeepBlue,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .offset(y = -(10.dp))
                )

                // 초대 링크 공유 버튼
                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(43.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(LightWhite)
                        .clickable {
                            // 초대 링크 복사
                            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Invite Link", inviteLink)
                            clipboardManager.setPrimaryClip(clip)
                            Toast.makeText(context, "초대 링크가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "링크 공유해서 친구 초대하기",
                        color = DeepBlue,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                // 공유 폴더 이동 버튼
                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(43.dp)
                        .offset(y = 60.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(LightWhite)
                        .clickable {
                            Toast.makeText(context, "공유 폴더로 이동합니다.", Toast.LENGTH_SHORT).show()
                            // 폴더 이동 기능 추가
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "공유 폴더 가기",
                        color = DeepBlue,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFolder() {
    val navController = NavHostController(LocalContext.current)
    MembersFolder(navController = navController)
}




