import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddContract
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel

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
    val context = LocalContext.current
    // viewState를 State로 수집
    val viewState by viewModel.viewState.collectAsState()
    // viewState로부터 로딩 상태를 가져옵니다
    val showLoading = viewState.isLoading

    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { navController.navigateUp() }
            )
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                if (showLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(100.dp))
                } else {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp), // 충분한 공간을 제공합니다
                            contentAlignment = Alignment.TopCenter // 상단 중앙에 정렬
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                                contentDescription = "Center Image",
                                modifier = Modifier
                                    .offset(y = 120.dp) // y 좌표를 조정하여 위치를 변경합니다
                            )
                        }

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

                        TextField(
                            value = groupName,
                            onValueChange = { groupName = it },
                            label = { Text("그룹 이름") },
                            modifier = Modifier.fillMaxWidth()
                        )

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
                                    .offset(x = -(10.dp)),
                                contentScale = ContentScale.FillBounds,
                                colorFilter = ColorFilter.tint(SteelBlue) // 색상 조절
                            )
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 10.dp)
                        ) {
                            memberNames.forEach { name ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Text(
                                        text = name,
                                        fontWeight = FontWeight.Bold,
                                        color = SteelBlue,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_close_26),
                                        contentDescription = "Remove Button",
                                        modifier = Modifier
                                            .clickable {
                                                memberNames = memberNames - name
                                                memberCount = memberNames.size
                                            }
                                            .size(24.dp)
                                    )
                                }
                            }
                        }

                        Text(text = "현재 멤버 수: $memberCount")

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
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    MembersNameScreen()
}
