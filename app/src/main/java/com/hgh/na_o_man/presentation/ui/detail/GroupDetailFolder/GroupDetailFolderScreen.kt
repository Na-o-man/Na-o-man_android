package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import CloudBtn
import SmallCloudBtn
import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.hgh.na_o_man.R
import com.hgh.na_o_man.domain.model.FolderDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.groupdetail.Bigfolder
import com.hgh.na_o_man.presentation.component.groupdetail.GroupInfo
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity.Companion.GROUP_DETAIL
import kotlin.math.abs


@ExperimentalPagerApi
@Composable
fun GroupDetailFolderScreen(
    navigationMyPage: (Long) -> Unit,
    navigationPhotoList: (Long, Long, Long) -> Unit,
    navigationVote: (Long) -> Unit,
    navigationBack: () -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: GroupDetailFolderViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    val context = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris: List<Uri> ->
       if (uris.isNotEmpty()){
           viewModel.setEvent(GroupDetailFolderContract.GroupDetailFolderEvent.OnUploadPhotoClicked(uris))
       }
    }


    var currentProfileId by remember { mutableStateOf<Long?>(null) }
    val groupId = remember { context.intent.getLongExtra(GROUP_DETAIL, 0L) }


    LaunchedEffect(groupId) {
        viewModel.setEvent(GroupDetailFolderContract.GroupDetailFolderEvent.InitGroupDetailFolderScreen(groupId))
        Log.d("GroupDetailScreen", "LaunchedEffect triggered for groupId: $groupId")
    }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoList -> {
                    navigationPhotoList(effect.groupId, effect.profiledId, effect.memberId)
                }

                is GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviVote -> {
                    navigationVote(effect.groupId)
                }

                is GroupDetailFolderContract.GroupDetailFolderSideEffect.NaviPhotoPicker -> {
                    imagePickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                    )
                }

                is GroupDetailFolderContract.GroupDetailFolderSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }


    Log.d("리컴포저블", "GroupDetailScreen")

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
                    StartAppBar(
                        onStartClick = {
                            navigationBack()
                        }
                    )
                },
                containerColor = Color(0xFFBBCFE5)

            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) {
                    EndTopCloud()
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(100.dp))

                        Box(
                            modifier = Modifier
                                .offset(x = -50.dp)
                                .fillMaxWidth(),
                            Alignment.CenterEnd
                        ) {
                            viewState.groupDetail?.let { groupDetail ->
                                GroupInfo(
                                    title = groupDetail.name,
                                    participantCount = groupDetail.memberCount,
                                    date = groupDetail.createdAt
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        viewState.groupDetail?.let { groupDetail ->
                            // Filter out folders without memberId

                            val filteredProfileInfoList =
                                groupDetail.profileInfoList.filter { it.memberId > 0 }

                            val folderDummyList = filteredProfileInfoList.map {
                                FolderDummy(
                                    imageRes = it.image, // ProfileInfo에 있는 image와 name을 FolderDummy에 맞게 매핑합니다.
                                    name = it.name
                                )
                            }

                            val folderList = folderDummyList + listOf(
                                FolderDummy(
                                    imageRes = "https://i.ibb.co/BKpgBf1/na-o-man-null-img.png",
                                    name = "others"
                                ),
                                FolderDummy(
                                    imageRes = "https://i.ibb.co/BKpgBf1/na-o-man-null-img.png",
                                    name = "all"
                                )
                            )

                            val pageCount = Int.MAX_VALUE
                            val realSize = folderList.size
                            val middlePage = pageCount / 2

                            val pagerState = rememberPagerState(middlePage - (middlePage % realSize)+viewState.pagerIndex)

                            LaunchedEffect(pagerState) {
                                snapshotFlow { pagerState.currentPage }
                                    .collect { page ->
                                        val folderInfo = folderList[page % realSize]
                                        // 현재 페이지의 profileId를 가져와 currentProfileId에 저장합니다.
                                        currentProfileId = when (folderInfo.name) {
                                            "all" -> 100L
                                            "others" -> 101L
                                            else -> filteredProfileInfoList.getOrNull(page % realSize)?.profileId
                                        }
                                    }
                            }


                            Box(
                                modifier = Modifier
                                    .height(264.dp)
                                    .fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_folder_600),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .width(800.dp)
                                        .align(Alignment.BottomCenter)
                                )


                                HorizontalPager(
                                    count = pageCount,
                                    state = pagerState,
                                    contentPadding = PaddingValues(horizontal = 70.dp), // Set padding to create partial view
                                    modifier = Modifier.matchParentSize(),
                                    itemSpacing = 0.dp,
                                ) { index ->

                                    val page = index % realSize // 무한 스크롤 인덱스 조정

                                    val currentPage = pagerState.currentPage
                                    val pageOffset = pagerState.currentPageOffset
                                    val pagePosition = index - currentPage - pageOffset

                                    // 페이지가 중앙에 가까울수록 더 커지도록 설정
                                    val targetScale = 0.8f + (1.2f - 0.8f) * (1 - abs(pagePosition))

                                    // 스케일 애니메이션
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .graphicsLayer {
                                                scaleX = targetScale
                                                scaleY = targetScale
                                                transformOrigin =
                                                    androidx.compose.ui.graphics.TransformOrigin(
                                                        0.5f,
                                                        1f
                                                    ) // 변환 원점을 아래쪽 중앙으로 설정
                                            }
                                    ) {
                                        val folderInfo = folderList[page]

                                        Bigfolder(
                                            folderInfo = folderInfo,
                                            onClick = {

                                                val memberId =
                                                    filteredProfileInfoList.getOrNull(page)?.memberId ?: -1
                                                val profileId = when {
                                                    filteredProfileInfoList.getOrNull(page)?.profileId != null -> filteredProfileInfoList[page].profileId
                                                    folderInfo.name == "all" -> 100L
                                                    folderInfo.name == "others" -> 101L
                                                    else -> -1L
                                                }
                                                Log.d("GroupDetailFolderScreen","groupId : $groupId, profileId : $profileId")
                                                currentProfileId = profileId
                                                Log.d("GroupDetailFolderScreen", "Updated currentProfileId: $currentProfileId")
                                                viewModel.setEvent(GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked(
                                                    profileId,
                                                    memberId,
                                                    currentPage - middlePage
                                                ))
                                            },
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                pageCount = realSize,
                                pageIndexMapping = { page -> page % realSize },
                                inactiveColor = Color.White,
                                activeColor = Color(0xFF6E6BFF),
                                indicatorWidth = if(realSize<=10)7.dp else 5.dp,
                                indicatorHeight = if(realSize<=10)7.dp else 5.dp, // Set the height of the indicator
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp)
                            )
                        }


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 40.dp)
                        ) {
                            SmallCloudBtn(title = "이미지\n업로드") {
                                viewModel.setEvent(
                                    GroupDetailFolderContract.GroupDetailFolderEvent.OnUploadClicked
                                )
                            }

                            CloudBtn(title = "다운로드") {
                                val profileId = currentProfileId ?: -1L
                                viewModel.setEvent(
                                    GroupDetailFolderContract.GroupDetailFolderEvent.OnDownloadClicked(profileId)
                                )
                            }

                            SmallCloudBtn(title = "지난\n안건") {
                                viewModel.setEvent(
                                    GroupDetailFolderContract.GroupDetailFolderEvent.OnVoteClicked
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun PreView(
) {
    GroupDetailFolderScreen(
        navigationMyPage = {},
        navigationPhotoList = { _, _, _ -> },
        navigationVote = { _ -> },
        navigationBack = {})
}