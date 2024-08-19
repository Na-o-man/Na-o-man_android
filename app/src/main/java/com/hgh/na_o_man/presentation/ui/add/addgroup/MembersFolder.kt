package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
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
    navigationHome: () -> Unit
) {
    Log.d("리컴포저블", "MembersFolder")

    val context = LocalContext.current as Activity
    val state by viewModel.viewState.collectAsState()

    val isGroupCreated = remember { state.isGroupCreated }

    BackHandler {
        navigationHome()
    }

    LaunchedEffect(isGroupCreated) {
        if (isGroupCreated) {
            navController.navigate(AddScreenRoute.FOLDER.route)
        }
    }


    Scaffold(
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
        ) {
            StartTopCloud()

            // 중앙 정렬 박스
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 10.dp)
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_file_227),
                    contentDescription = null,
                    modifier = Modifier
                        .size(221.dp, 194.26.dp)
                        .align(Alignment.Center)
                        .graphicsLayer(
                            alpha = 0.8f,
                            shape = RoundedCornerShape(16.dp),
                            clip = true
                        )
                )

                // 그룹 이름 텍스트
                Text(
                    text = state.groupName,
                    color = DeepBlue,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 15.dp)
                )

                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 링크 복사 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 160.dp, start = 205.dp)
                        .align(Alignment.Center)
                        .clickable {
                            val clip = ClipData.newPlainText("Copied Link", state.inviteLink)
                            clipboardManager.setPrimaryClip(clip)
                            Toast.makeText(context, "링크가 복사되었습니다.", Toast.LENGTH_SHORT).show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cloud_138),
                        contentDescription = null,
                        modifier = Modifier
                            .size(99.dp, 65.dp)
                            .graphicsLayer {
                                ColorFilter.tint(LightWhite.copy(0.9f))
                            }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center).padding(1.dp)
                    ) {
                        Text(
                            text = "링크복사",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = SteelBlue
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_link_copy_158),
                            contentDescription = null,
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 135.dp, start = 40.dp, end = 40.dp)
                ) {
                    // 초대 링크 공유 버튼
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(LightWhite.copy(0.4f))
                            .clickable {
                                // 카카오톡 공유
                                shareInviteLink(context, state.inviteLink)
                            }
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .border(
                                    width = 1.dp,
                                    color = LightWhite.copy(alpha = 0.32f),
                                    shape = RoundedCornerShape(50.dp)
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "링크 공유해서 친구 초대하기",
                            color = DeepBlue,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 공유 폴더 이동 버튼
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(LightWhite.copy(0.35f))
                            .clickable {
                                Toast.makeText(context, "공유 폴더로 이동합니다.", Toast.LENGTH_SHORT).show()
                                navigationHome()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .border(
                                    width = 1.dp,
                                    color = LightWhite.copy(alpha = 0.32f),
                                    shape = RoundedCornerShape(50.dp)
                                ),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = "공유 폴더 가기",
                            color = DeepBlue,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}

// 카카오톡 공유 인텐트 함수
fun shareInviteLink(context: Context, inviteLink: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, inviteLink)
        type = "text/plain"
        setPackage("com.kakao.talk")
    }
    context.startActivity(sendIntent)
}
