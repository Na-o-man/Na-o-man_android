package com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder

import CloudBtn
import SmallCloudBtn
import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.domain.model.FolderDummy
import com.hgh.na_o_man.presentation.base.LoadState
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.FolderProfile
import com.hgh.na_o_man.presentation.component.StartEndAppBar
import com.hgh.na_o_man.presentation.component.StateErrorScreen
import com.hgh.na_o_man.presentation.component.StateLoadingScreen
import com.hgh.na_o_man.presentation.component.groupdetail.Bigfolder
import com.hgh.na_o_man.presentation.component.groupdetail.GroupInfo
import com.hgh.na_o_man.presentation.component.groupdetail.SmallFolder
import com.hgh.na_o_man.presentation.ui.detail.ALL_PHOTO_ID
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailActivity.Companion.GROUP_DETAIL
import kotlinx.coroutines.launch
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
        viewModel.uploadUri(uris)
    }

    val groupId = remember { context.intent.getLongExtra(GROUP_DETAIL, 0L) }

    LaunchedEffect(groupId) {
        viewModel.initGroupId(groupId)
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

                else -> Unit
            }
        }
    }


    Log.d("리컴포저블", "GroupDetailScreen")

    // 페이저용
    val pagerState = rememberPagerState(initialPage = 0)


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
                    StartEndAppBar(
                        onStartClick = {
                            navigationBack()
                        },
                        onEndClick = {
                            navigationMyPage(viewState.groupId)
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
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(100.dp))

                        Box(
                            modifier = Modifier.padding(start = 200.dp)
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
                                    imageRes = R.drawable.ic_example.toString(),
                                    name = "others"
                                ),
                                FolderDummy(
                                    imageRes = R.drawable.ic_example.toString(),
                                    name = "all"
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .height(264.dp)
                                    .fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_folder_600),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.width(800.dp).align(Alignment.BottomCenter)
                                )

                                HorizontalPager(
                                    count = folderList.size,
                                    state = pagerState,
                                    contentPadding = PaddingValues(horizontal = 70.dp), // Set padding to create partial view
                                    modifier = Modifier.matchParentSize(),
                                    itemSpacing = 0.dp,

                                ) { page ->

                                    val currentPage = pagerState.currentPage
                                    val pageOffset = pagerState.currentPageOffset
                                    val pagePosition = page - currentPage - pageOffset

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
                                                    filteredProfileInfoList.getOrNull(page)?.memberId
                                                        ?: -1
//                                            val profileId = filteredProfileInfoList.getOrNull(page)?.profileId ?: 100L
                                                val profileId = when {
                                                    filteredProfileInfoList.getOrNull(page)?.profileId != null -> filteredProfileInfoList[page].profileId
                                                    folderInfo.name == "all" -> 100L
                                                    folderInfo.name == "others" -> 101L
                                                    else -> -1L
                                                }
                                                navigationPhotoList(
                                                    groupDetail.shareGroupId,
                                                    profileId,
                                                    memberId
                                                )
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp))

                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp),
                                pageCount = folderList.size
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
                                // 테스트용 - 삭제해야함
                                viewModel.setEvent(
                                    GroupDetailFolderContract.GroupDetailFolderEvent.OnUserFolderClicked(
                                        profileId = ALL_PHOTO_ID,
                                        memberId = -1L,
                                    )
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