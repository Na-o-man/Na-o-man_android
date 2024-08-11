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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.StartTopCloud
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
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

    val groupName = state.groupName.ifEmpty { "제주도2024" } // 기본 그룹 이름
    val isGroupCreated = state.isGroupCreated
    val inviteLink = state.inviteLink

    LaunchedEffect(isGroupCreated) {
        if (isGroupCreated) {
            navController.navigate(AddScreenRoute.FOLDER.route)
        }
    }

    LaunchedEffect(Unit) {
        // 그룹 이름을 API에서 가져와서 업데이트
        viewModel.handleEvents(AddContract.AddEvent.UpdateGroupName("제주도2024")) // 예시, 실제 API 호출로 대체
    }

    Scaffold(
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            StartTopCloud()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .wrapContentSize()
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_share_folder_144),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .offset(x = 10.dp, y = -(20.dp))
                        .size(200.dp)
                )

                // 그룹 이름 텍스트
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = -(10.dp)) // 위로 이동
                ) {
                    Text(
                        text = groupName,
                        color = DeepBlue,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                val clipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val linkToCopy = "http://yourlink.com"

                // 구름 이미지와 텍스트를 함께 겹쳐서 배치
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = 150.dp, y = 107.dp)
                        .clickable {
                            // 링크 복사 이벤트
                            val clip = ClipData.newPlainText("Copied Link", linkToCopy)
                            clipboardManager.setPrimaryClip(clip)
                            Toast.makeText(context, "링크가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // 구름 이미지
                    Image(
                        painter = painterResource(id = R.drawable.ic_button_cloud_next_140),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    // "링크복사" 텍스트 (구름 이미지 위에 배치)
                    Text(
                        text = "링크복사",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SteelBlue,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(43.dp)
                        .offset(y = 200.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(LightWhite)
                        .clickable {
                            val clipboardManager2 =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Invite Link", inviteLink)
                            clipboardManager2.setPrimaryClip(clip)
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

                Box(
                    modifier = Modifier
                        .width(220.dp)
                        .height(43.dp)
                        .offset(y = 250.dp)
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







