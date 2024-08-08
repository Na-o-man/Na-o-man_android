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
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
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
    var groupName by remember { mutableStateOf("") }
    var memberCount by remember { mutableIntStateOf(0) }
    var memberNames by remember { mutableStateOf(listOf<String>()) }
    var newMemberName by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewState by viewModel.viewState.collectAsState()
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
                                .height(200.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                                contentDescription = "Center Image",
                                modifier = Modifier
                                    .offset(y = 70.dp)
                            )
                        }

                        Text(
                            text = "사진을 공유할 사람들의 이름을 추가해주세요.",
                            modifier = Modifier
                                .offset(y = -(105.dp))
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

//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share_folder_144),
                            contentDescription = "그룹 이미지",
                            modifier = Modifier
                                .offset(y = -(100.dp))
                                .size(240.dp)
                                .fillMaxSize()
                        )
                        // Names overlaid on the image
                        Column(
                            modifier = Modifier
                                .padding(16.dp) // Add padding around names
                        ) {
                            memberNames.chunked(3).forEach { rowNames ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    rowNames.forEach { name ->
                                        Text(
                                            text = name,
                                            fontWeight = FontWeight.Bold,
                                            color = SteelBlue,
                                            modifier = Modifier
                                                .background(Color.White.copy(alpha = 0.7f))
                                                .padding(4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // TextField for entering new member name
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(225.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(LightWhite.copy(alpha = 0.5f))
                    ) {
                        BasicTextField(
                            value = newMemberName,
                            onValueChange = { newValue ->
                                newMemberName = newValue
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                                .align(Alignment.Center)
                                .onFocusChanged { state ->
                                    isFocused = state.isFocused
                                },
                            textStyle = TextStyle(
                                color = SteelBlue,
                                background = Color.Transparent,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            ),
                            cursorBrush = SolidColor(SteelBlue),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (newMemberName.isEmpty() && !isFocused) {
                                        Text(
                                            text = "이름",
                                            color = SteelBlue,
                                            textAlign = TextAlign.Center,
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
                                .height(24.dp)
                                .width(26.dp)
                                .offset(x = (-10).dp)
                                .clickable {
                                    if (newMemberName.isNotBlank()) {
                                        memberNames = memberNames + newMemberName
                                        memberCount = memberNames.size
                                        newMemberName = "" // Reset the text field after adding
                                    }
                                },
                            contentScale = ContentScale.FillBounds,
                            colorFilter = ColorFilter.tint(SteelBlue)
                        )
                    }

                    Column(
                        modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
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
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    MembersNameScreen()
}
