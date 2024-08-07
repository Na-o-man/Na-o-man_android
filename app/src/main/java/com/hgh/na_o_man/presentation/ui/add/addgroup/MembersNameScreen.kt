package com.hgh.na_o_man.presentation.ui.add.addgroup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.NextAppBar1
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.AddScreenRoute

@Composable
fun MembersNameScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "members_name_screen")

    var groupName by remember { mutableStateOf("") }
    var memberCount by remember { mutableIntStateOf(0) }
    var memberNames by remember { mutableStateOf(listOf<String>()) }
    var newMemberName by remember { mutableStateOf("") }
    val context = LocalContext.current // LocalContext를 통해 context를 가져옴

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = {
                    navController.navigateUp()// 뒤로가기 동작 처리
                }
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
            // 화면 중앙 이미지
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 120.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Center Image"
                )
            }

            // 텍스트
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = -(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "사진을 공유할 사람들의 이름을 추가해주세요.",
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2 + 10.dp.toPx()
                            drawLine(
                                color = LightWhite,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold
                )

                Column(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 그룹 이름 입력
                    TextField(
                        value = groupName,
                        onValueChange = { groupName = it },
                        label = { Text("그룹 이름") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 이름 추가 버튼
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(225.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .clickable {
                                if (newMemberName.isNotBlank()) {
                                    memberNames = memberNames + newMemberName
                                    memberCount = memberNames.size
                                    newMemberName = ""
                                }
                            }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_group_detail_info_151),
                            contentDescription = "Lower Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )

                        BasicTextField(
                            value = newMemberName,
                            onValueChange = { newMemberName = it },
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .offset(y = 10.dp)
                                .padding(horizontal = 8.dp),
                            textStyle = TextStyle(color = SteelBlue)
                        )

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31),
                            contentDescription = "Add Button",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .height(24.dp)
                                .width(26.dp)
                                .offset(x = -(10.dp), y = 0.dp)
                                .clickable {
                                    if (newMemberName.isNotBlank()) {
                                        memberNames = memberNames + newMemberName
                                        memberCount = memberNames.size
                                        newMemberName = ""
                                    }
                                },
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    // 멤버 리스트 표시
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp)
                    ) {
                        memberNames.forEach { name ->
                            Text(text = name, fontWeight = FontWeight.Bold, color = SteelBlue)
                        }
                    }

                    // 현재 멤버 수 표시
                    Text(text = "현재 멤버 수: $memberCount")

                    // 사이드 이펙트 수집
                    LaunchedEffect(Unit) {
                        viewModel.effect.collect { effect ->
                            when (effect) {
                                is AddContract.AddSideEffect.NavigateToNextScreen -> {
                                    navController.navigate(AddScreenRoute.ADJECTIVE.route)
                                }
                                is AddContract.AddSideEffect.ShowToast -> {
                                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                                }
                                is AddContract.AddSideEffect.ShowError -> {
                                    Toast.makeText(context, effect.error, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }

                    // 다음 버튼
                    NextAppBar1(
                        onNextClick = {
                            if (memberNames.isNotEmpty()) {
                                navController.navigate(AddScreenRoute.ADJECTIVE.route)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    // NavController 사용을 피하기 위해 별도 처리 필요
    MembersNameScreen()
}





