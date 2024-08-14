import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var memberNames by remember { mutableStateOf(listOf<String>()) }
    var newMemberName by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewState by viewModel.viewState.collectAsState()
    val showLoading = viewState.isLoading

    Scaffold(
        topBar = {
            StartAppBar(onStartClick = { navController.popBackStack() })
        },
        bottomBar = {
            NextAppBar1(
                onNextClick = {
                    if (memberNames.isNotEmpty()) {
                        viewModel.handleEvents(AddContract.AddEvent.AddMember(newMemberName))
                        navController.navigate(AddScreenRoute.ADJECTIVE.route)
                    }
                }, modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

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
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .padding(top = 20.dp), // 상단 패딩 조정
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // 중앙에 위치한 이미지
                        Box(
                            modifier = Modifier
                                .size(23.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                                contentDescription = "Center Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp) // 적절한 높이 조정
                            )
                        }

                        // 설명 텍스트
                        Text(
                            text = "사진을 공유할 사람들의 이름을 추가해주세요.",
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .drawBehind {
                                    val strokeWidth = 1.dp.toPx()
                                    val y = size.height - strokeWidth / 2
                                    drawLine(
                                        color = LightWhite,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = strokeWidth
                                    )
                                },
                            color = LightWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 중앙에 위치한 큰 이미지
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp), // 상단 패딩 조정
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(10.dp), // 내부 패딩 추가
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_share_folder_144),
                                contentDescription = "ADD",
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.FillBounds
                            )

                            // 큰 이미지 내부에 이름 목록을 배치하고 스크롤 가능하게 설정
                            LazyColumn(
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 35.dp, bottom = 20.dp, end = 10.dp)
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp), // 좌우 padding
                                verticalArrangement = Arrangement.Center // 내용을 중앙에서부터 채우도록 설정
                            ) {
                                memberNames.chunked(3).forEach { rowNames -> // 3개의 이름씩 묶어 Row에 배치
                                    item {
                                        LazyRow(
                                            modifier = Modifier
                                                .fillMaxWidth() // Row가 전체 너비를 차지하도록 설정
                                                .padding(vertical = 8.dp), // 각 Row 간격 조정
                                            horizontalArrangement = Arrangement.spacedBy(8.dp) // 이름들 사이 간격 조정
                                        ) {
                                            items(rowNames) { name ->
                                                var showRemoveButton by remember { mutableStateOf(false) }

                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .background(Color.White.copy(alpha = 0.7f)) // 흰색 배경 추가
                                                        .padding(horizontal = 4.dp, vertical = 3.dp)
                                                        .clickable {
                                                            showRemoveButton = !showRemoveButton // 클릭 시 삭제 버튼 표시/숨김
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                                        Text(
                                                            text = name,
                                                            fontWeight = FontWeight.Bold,
                                                            color = SteelBlue,
                                                            fontSize = 14.sp
                                                        )

                                                        if (showRemoveButton) {
                                                            Image(
                                                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_close_26),
                                                                contentDescription = "Remove Button",
                                                                modifier = Modifier
                                                                    .clickable {
                                                                        memberNames = memberNames - name
                                                                        viewModel.handleEvents(
                                                                            AddContract.AddEvent.RemoveMember(name)
                                                                        )
                                                                    }
                                                                    .size(13.dp) // 이미지 크기를 줄여서 테스트
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // 입력창
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(start = 50.dp, end = 50.dp, top = 390.dp)
                            .height(100.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(LightWhite.copy(alpha = 0.5f))
                    ) {
                        BasicTextField(
                            value = newMemberName,
                            onValueChange = { newMemberName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                                .align(Alignment.CenterStart)
                                .onFocusChanged { state -> isFocused = state.isFocused },
                            textStyle = TextStyle(
                                color = SteelBlue,
                                background = Color.Transparent,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Start
                            ),
                            cursorBrush = SolidColor(SteelBlue),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterStart),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (newMemberName.isEmpty() && !isFocused) {
                                        Text(
                                            text = "이름",
                                            color = SteelBlue,
                                            textAlign = TextAlign.Start,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )

                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31),
                            contentDescription = "Add Button",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(40.dp, 28.dp)
                                .padding(end = 10.dp)
                                .clickable {
                                    if (newMemberName.isNotBlank()) {
                                        if (memberNames.contains(newMemberName)) {
                                            Toast.makeText(
                                                context,
                                                "이미 존재하는 이름입니다.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            memberNames = memberNames + newMemberName
                                            viewModel.handleEvents(
                                                AddContract.AddEvent.AddMember(
                                                    newMemberName
                                                )
                                            )
                                            newMemberName = "" // 추가 후 입력 필드 리셋
                                        }
                                    }
                                },
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
            }

            // 사이드 이펙트 처리
            LaunchedEffect(Unit) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is AddContract.AddSideEffect.NavigateToNextScreen -> {
                            navController.navigate(AddScreenRoute.ADJECTIVE.route)
                        }

                        is AddContract.AddSideEffect.ShowToast -> {
                            Toast.makeText(
                                context,
                                effect.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is AddContract.AddSideEffect.ShowError -> {
                            Toast.makeText(
                                context,
                                effect.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}