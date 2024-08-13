package com.hgh.na_o_man.presentation.ui.detail.vote

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.VoteDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.PlusAppBar
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartBottomCloud
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StartPlusAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.homeIcon.NoGroupBox
import com.hgh.na_o_man.presentation.theme.SteelBlue
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity.Companion.GROUP_DETAIL
import com.hgh.na_o_man.presentation.ui.main.home.GroupListScreen
import com.hgh.na_o_man.presentation.ui.main.home.HomeContract
import getVoteList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun VoteMainScreen(
    groupId : Long,
    navigationAgenda: () -> Unit,
    navigationBack: () -> Unit,
    viewModel: VoteMainViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity

    // 드롭다운 메뉴
    var expanded by remember { mutableStateOf(false) }
    var selectedGroupName by remember { mutableStateOf(viewState.groupName) }

    // FocusRequester 초기화
    val focusRequester = remember { FocusRequester() }

    Log.d("리컴포저블", "VoteMainScreen")
    Log.d("VoteMainScreen", "Current view state: $viewState")

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.setEvent(VoteMainContract.VoteMainEvent.InitVoteMainScreen)
    }

    LaunchedEffect(groupId) {
        Log.d("VoteMainScreen", "Received groupId: $groupId")
    }


    when (viewState.loadState) {
        LoadState.LOADING -> {
            StateLoadingScreen()
        }

        LoadState.ERROR -> {
            StateErrorScreen()
        }

        LoadState.SUCCESS -> {
            Scaffold(
                topBar = {
                    StartPlusAppBar(
                        onStartClick = {
                        /*TODO*/
                        },
                        onEndClick = {
                        /*TODO*/
                        }
                    )
                },
                containerColor = lightSkyBlue
            ) { padding ->
                // 구름 배경 Box
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                    StartBottomCloud()
                }


                Box(
                    modifier = Modifier
                        .fillMaxSize() // 전체 화면을 채우도록 설정
                        .padding(start = 30.dp, top = 100.dp)
                ) {

                    Log.d("VoteMainScreen", "DropdownMenu - expanded: $expanded, selectedGroupName: $selectedGroupName")
                    voteListDropDownMenu(
                        viewModel = viewModel,
                        selectedGroupName = selectedGroupName,
                        onGroupSelected = { groupName ->
                            selectedGroupName = groupName
                        },
                    )
                }

                if (viewState.voteList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(), // 전체 화면을 채우도록 설정
                        contentAlignment = Alignment.Center // 중앙 정렬
                    ) {
                        NoGroupBox(message = "아직 안건이 없어요.\n플러스 버튼을 눌러\n그룹을 추가해 주세요.",
                            "안건 추가하기",
                            onAddGroupInBoxClicked = { viewModel.setEvent(VoteMainContract.VoteMainEvent.onAddAgendaInBoxClicked) })
                    }
                } else {
                    VoteListScreen(
                        viewModel = viewModel,
                        voteList = viewState.voteList,
                        modifier = Modifier.padding(padding)
                    )
                }

                LaunchedEffect(expanded) {
                    if (expanded) {
                        focusRequester.requestFocus()  // 드롭다운이 확장된 후에 포커스 요청
                    }
                }
            }
        }
    }
}


@Composable
fun VoteListScreen(
    viewModel: VoteMainViewModel = hiltViewModel(),
    voteList:List<VoteDummy>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp, start = 16.dp, end = 16.dp, bottom = 16.dp) // 위쪽에 여백을 추가
        ) {
            items(voteList) { vote ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                getVoteList(
                    title = vote.title,
                    images = vote.images
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun voteListDropDownMenu(
    viewModel: VoteMainViewModel = hiltViewModel(),
    selectedGroupName : String,
    onGroupSelected: (String) -> Unit,
){
    val viewState by viewModel.viewState.collectAsState()

    // 드롭다운 메뉴의 확장 상태를 관리하는 상태 변수
    var expanded by remember { mutableStateOf(false) }

    // 만약 selectedGroupName이 비어있다면 ViewModel에서 가져온 groupName을 기본값으로 설정
    val initialGroupName = if (selectedGroupName.isEmpty()) {
        viewState.groupName // ViewModel에서 가져온 groupName을 기본값으로 설정
    } else {
        selectedGroupName
    }

    // 드롭다운 메뉴를 누르면 확장 상태 변경
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // 그룹 이름을 선택하는 텍스트 필드
        OutlinedTextField(
            value = initialGroupName,
            onValueChange = {},
            readOnly = true, // 사용자가 직접 입력하지 못하도록 설정
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown arrow")
            },
            modifier = Modifier
                .wrapContentWidth()
                .menuAnchor()
        )

        // 그룹 이름 목록을 표시하는 드롭다운 메뉴
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false // 드롭다운 메뉴 닫기
            }
        ) {
            // 그룹 이름 리스트를 순회하며 각각의 아이템을 드롭다운 메뉴에 추가
            viewState.groupNameList.forEach { group ->
                DropdownMenuItem(
                    onClick = {
                        onGroupSelected(group.name) // 선택한 그룹 이름 반환
                        expanded = false // 드롭다운 메뉴 닫기
                    }
                ) {
                    Text(text = group.name)
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun PreviewVoteMainScreen() {
//    val navController = NavHostController(context = LocalContext.current) // NavHostController 초기화
//    VoteMainScreen(groupId = 1L ,navigationBack = {}, navigationAgenda = {}) // 초기화한 navController 전달
//}