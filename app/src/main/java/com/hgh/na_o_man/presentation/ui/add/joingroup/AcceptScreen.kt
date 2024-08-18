package com.hgh.na_o_man.presentation.ui.add.joingroup

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.component.EndTopCloud
import com.hgh.na_o_man.presentation.component.StartAppBar
import com.hgh.na_o_man.presentation.theme.DeepBlue
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinContract.JoinSideEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AcceptScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    navigationBack: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val memberList = viewState.members
    val membersPerPage = 3
    val pagerState = rememberPagerState(pageCount = { (memberList.size + membersPerPage - 1) / membersPerPage })

    var selectedProfile by remember { mutableStateOf<Member?>(null) }
    val context = LocalContext.current as Activity

    Scaffold(
        topBar = {
            StartAppBar(onStartClick = {
                navigationBack()
            })
        },
        containerColor = lightSkyBlue
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EndTopCloud()

            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = padding.calculateTopPadding() + 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_nangman_23),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .size(18.dp)
                        .graphicsLayer(rotationZ = -120f)
                )

                Text(
                    text = "당신이 누구인지 알려주세요.",
                    modifier = Modifier.padding(start = 16.dp),
                    color = LightWhite,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 180.dp, end = 20.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    val startIndex = page * membersPerPage
                    val endIndex = minOf(startIndex + membersPerPage, memberList.size)
                    val pageMembers = memberList.subList(startIndex, endIndex)

                    AcceptWho1(
                        navController = navController,
                        onProfileSelected = { profileName ->
                            selectedProfile = memberList.find { it.name == profileName }
                        },
                        members = pageMembers,
                        selectedProfile = selectedProfile,
                        currentPage = page
                    )
                }

                PageIndicator(
                    pagerState = pagerState,
                    totalPages = pagerState.pageCount // 페이지 수 설정
                )

                Box(
                    modifier = Modifier
                        .padding(bottom = 20.dp, start = 260.dp)
                        .offset(y = -(50.dp))
                        .clickable {
                            selectedProfile?.let { profile ->
                                viewModel.onNextButtonClicked(profile.id, viewState.shareGroupId)
                            } ?: run {
                                Toast.makeText(context, "프로필을 선택해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        },
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_button_cloud_next_140),
                        contentDescription = "Next Button",
                        modifier = Modifier.size(78.dp, 48.dp),
                    )
                }
            }
        }
    }

    LaunchedEffect(viewState) {
        if (viewState.profileId > 0 && viewState.shareGroupId > 0) {
            viewModel.onNextButtonClicked(viewState.profileId, viewState.shareGroupId)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { sideEffect ->
            when (sideEffect) {
                is JoinSideEffect._ShowToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
                is JoinSideEffect.FinishActivity -> {
                    context.finish()
                }
                else -> Unit
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    pagerState: PagerState,
    totalPages: Int
) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val color = if (pagerState.currentPage == index) DeepBlue else Color.Gray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(4.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}
