package com.hgh.na_o_man.presentation.ui.detail.agenda

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.PlusAppBar
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartBottomCloud
import com.hgh.na_o_man.presentation.component.voteIcon.DropDownMenu
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightNavy
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue


@Composable
fun AgendaScreen(
    navigationBack: () -> Unit,
    navigationPhotoList: (Long, Long) -> Unit,
    navController: NavController,
    // viewModel: PhotoListViewModel = hiltViewModel(),
) {
    Log.d("리컴포저블", "VoteScreen2")
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val data = remember {
        savedStateHandle?.get<List<Dummy>>("agendaData")
    }

    Log.d("더미", "${data}")

    var showDialog by remember { mutableStateOf(false) } // 다이얼로그 표시 상태
    var images by remember { mutableStateOf(listOf<String>()) }
    var agendaText by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            StartAppBar(
                onStartClick = { /* TODO: Handle start click */ }
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                PlusAppBar(
                    onPlusClick = {
                        navigationPhotoList(22, 24)
                    }
                )
            }
        },
        containerColor = lightSkyBlue
    ) { padding ->
        // 구름 배경 Box
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()
        }

//        Box(modifier = Modifier.fillMaxSize()) {
//            StartBottomCloud()
//        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .offset(x = 0.dp, y = 160.dp)
                .background(color = LightWhite, shape = RoundedCornerShape(20.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // AgendaInputSection 호출
                AgendaInputSection(
                    agendaText = agendaText,
                    onValueChange = { agendaText = it },
                    onFocusChange = { isFocused = it }
                )
            }
        }

        CenterContent()

//        // 기본 이미지 쌍 추가
//        LaunchedEffect(Unit) {
//            images.add(Pair(R.drawable.ic_rectangle_260, R.drawable.ic_nangman_23)) // 첫 번째 이미지 쌍 추가
//            images.add(Pair(R.drawable.ic_rectangle_260, R.drawable.ic_nangman_23)) // 두 번째 이미지 쌍 추가
//        }

        // 배경 이미지와 이미지 쌍을 표시
        BackgroundImage()

//        // ImagePairContainer에 이미지 리스트와 추가 로직, 다이얼로그 상태를 전달
//        ImagePairContainer(images,
//            onAddImage = {
//                // 새로운 이미지 추가 로직
//                images.add(Pair(R.drawable.ic_rectangle_260, R.drawable.ic_nangman_23)) // 예시로 추가
//            },
//            showDialog = showDialog,
//            onShowDialogChange = { showDialog = it }
//        )

        // 다이얼로그 표시
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false }, // 다이얼로그 닫기
                title = { Text("경고") },
                text = { Text("이미지가 두 개 이상 있어야 합니다.") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("확인")
                    }
                }
            )
        }
    }

    DropDownImageButton() // 드롭다운 버튼 추가

    // 추가 이미지 버튼 호출
    AddImageButton(
        images = images,
        onAddImage = { /* 안건 추가 로직 */ },
        onShowDialogChange = { showDialog = it }
    )

    // 다이얼로그 표시
    if (showDialog) {
        ShowWarningDialog(onDismiss = { showDialog = false })
    }

    Box(modifier = Modifier.fillMaxSize()) {
        StartBottomCloud()
    }
}


@Composable
fun AgendaInputSection(
    agendaText: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AgendaLabel() // "안건" 텍스트
        Spacer(modifier = Modifier.width(16.dp)) // 텍스트와 입력 필드 사이에 공간 추가
        AgendaInputField(
            value = agendaText,
            onValueChange = onValueChange,
            onFocusChange = onFocusChange
        )
    }
}

@Composable
fun AgendaLabel() {
    Text(
        modifier = Modifier.padding(start = 15.dp),
        text = "안건",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = DeepBlue
    )
}

@Composable
fun AgendaInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) } // 이 부분을 여기로 이동
    var agendaText by remember { mutableStateOf("") }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .onFocusChanged { state -> onFocusChange(state.isFocused) }
            .padding(17.dp),
        textStyle = TextStyle(
            color = SteelBlue,
            background = Color.Transparent,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        ),
        cursorBrush = SolidColor(SteelBlue),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty() && !isFocused) {
                    Text(
                        text = "안건을 입력해주세요.",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                innerTextField() // 실제 입력 필드
            }
        }
    )
}

@Composable
fun CenterContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = 40.dp, y = 240.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 중앙 이미지
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                contentDescription = "Center Image",
                modifier = Modifier
                    .size(16.dp)
                    .graphicsLayer(rotationZ = -120f) // -120도 회전
            )

            // 텍스트
            Text(
                text = "+를 눌러 사진을 추가해주세요.\n ",
                color = LightWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 15.dp, start = 30.dp) // 텍스트와 이미지 간의 간격 설정
            )
        }
    }
}


@Composable
fun BackgroundImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(), // Box가 전체 화면을 차지하도록 설정
        contentAlignment = Alignment.Center // 내용 중앙 정렬
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rectangle_269), // 추가할 이미지 리소스
            contentDescription = "추가된 이미지",
            modifier = Modifier
                .offset(y = -10.dp)
                .height(210.dp)
                .width(310.dp)
                .blur(0.5.dp) // 이미지 블러 효과
        )
    }
}

@Composable
fun ImagePairContainer(
    images: List<Pair<Int, Int>>,
    onAddImage: () -> Unit,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 90.dp),
        verticalArrangement = Arrangement.Center, // 중앙 정렬
        horizontalAlignment = Alignment.End
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3열로 구성
            modifier = Modifier
                .fillMaxSize()
                .weight(2.5f)
                .padding(top = 380.dp), // 가변적인 공간을 차지
            contentPadding = PaddingValues(10.dp),
        ) {
            items(images) { imagePair ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(150.dp),
                    contentAlignment = Alignment.Center
                ) { // 아이템 간격 조절
                    CombinedImage(imagePair.first, imagePair.second) // 이미지 쌍 표시
                }
            }
        }
    }
}

@Composable
fun CombinedImage(imageId1: Int, imageId2: Int) {
    Box(modifier = Modifier.size(510.dp)) { // 이미지 크기를 설정
        Image(
            painter = painterResource(id = imageId1), // 첫 번째 이미지
            contentDescription = "첫 번째 이미지",
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth() // Box가 전체 폭을 차지하도록 설정
        )

        Image(
            painter = painterResource(id = imageId2), // 두 번째 이미지
            contentDescription = "두 번째 이미지",
            modifier = Modifier
                .align(Alignment.Center) // 중앙 정렬
                .size(15.dp) // 두 번째 이미지 크기 설정
                .graphicsLayer(rotationZ = -30f) // -30도 회전
        )
    }
}

@Composable
fun DynamicImageContainer(images: List<Pair<Int, Int>>) {
    val imageCount = images.size
    val boxSize = when {
        imageCount <= 2 -> 150.dp
        imageCount <= 4 -> 200.dp
        else -> 250.dp // 추가적인 이미지 개수에 따라 크기 조정
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(images) { imagePair ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(boxSize),
                    contentAlignment = Alignment.Center
                ) {
                    CombinedImage(imagePair.first, imagePair.second)
                }
            }
        }
    }
}

@Composable
fun DropDownImageButton() {
    var showDropDown by remember { mutableStateOf(false) } // 드롭다운 표시 상태
    var selectedName by remember { mutableStateOf("황지원") } // 선택된 이름 상태

    Box(
        modifier = Modifier
            .fillMaxSize() // Box가 전체 화면을 차지하도록 설정
            .offset(x = -55.dp, y = 48.dp), // 이미지 위쪽 여백
        contentAlignment = Alignment.CenterEnd // 내용 중앙 정렬
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_nav_plus_new_31), // 두 번째 이미지 리소스
            contentDescription = "세 번째 이미지",
            modifier = Modifier
                .blur(0.1.dp)
                .clickable {
                    showDropDown = !showDropDown // 이미지 클릭 시 드롭다운 표시 상태 토글
                }
        )

        // 드롭다운 메뉴 표시
        if (showDropDown) {
            DropDownMenu(
                isVisible = showDropDown,
                onDismiss = { showDropDown = false } // 드롭다운 메뉴 닫기
            )
        }
    }
}

@Composable
fun AddImageButton(
    images: List<String>,  // 이미지 리스트를 매개변수로 받음
    onAddImage: () -> Unit,
    onShowDialogChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = -40.dp, y = 520.dp), // 이미지 위쪽 여백
        contentAlignment = Alignment.BottomEnd // 내용 하단 정렬
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cloud_138), // 네 번째 이미지 리소스
            contentDescription = "네 번째 이미지",
            modifier = Modifier
                .blur(0.1.dp)
                .size(90.dp)
                .clickable {
                    // 이미지 개수 확인
                    if (images.size >= 2) {
                        onAddImage() // 안건 추가 로직 호출
                    } else {
                        // 경고 메시지 표시
                        onShowDialogChange(true) // 다이얼로그 표시 상태 변경
                    }
                }
        )

        // 텍스트 오버레이
        Text(
            text = "안건 추가",
            color = LightNavy, // 텍스트 색상
            modifier = Modifier
                .padding(bottom = 30.dp, end = 17.dp)
                .align(Alignment.BottomEnd) // 텍스트 중앙 정렬
        )
    }
}

@Composable
fun ShowWarningDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss, // 다이얼로그 닫기
        title = { Text("경고") },
        text = { Text("안건은 두 장 이상의 사진이 필요합\n니다. 사진을 더 선택해주세요. ") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("확인")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewVote2() {
    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
    AgendaScreen({}, { _, _ ->

    }, navController) // 초기화한 navController 전달
}