package com.hgh.na_o_man.presentation.ui.detail

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.hgh.na_o_man.domain.model.Dummy
import com.hgh.na_o_man.presentation.ui.detail.GroupDetailFolder.GroupDetailFolderScreen
import com.hgh.na_o_man.presentation.ui.detail.agenda.AgendaScreen
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListScreen
import com.hgh.na_o_man.presentation.ui.detail.vote.VoteScreen1
import com.hgh.na_o_man.presentation.ui.detail.vote_detail.VoteDetailScreen
import com.hgh.na_o_man.presentation.ui.main.MainScreenRoute
import com.hgh.na_o_man.presentation.ui.sign.SignScreenRoute

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GroupDetailScreen(
    navController: NavHostController = rememberNavController(),
) {

    Log.d("리컴포저블", "GroupDetailScreen")
    Scaffold(
        containerColor = Color(0xFFBBCFE5)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = GroupDetailScreenRoute.DETAIL.route
            ) {

                composable(route = GroupDetailScreenRoute.DETAIL.route) {
                    GroupDetailFolderScreen(
                        navigationBack = {
                            navController.popBackStack() // 수정 필요
                        },
                        navigationPhotoList = { groupId, memberId ->
                            navController.navigate(
                                GroupDetailScreenRoute.LIST.route.plus("/${groupId}")
                                    .plus("/${memberId}").plus("/${false}")
                            )
                        },
                        navigationVote = { groupId ->
                            navController.navigate(
                                GroupDetailScreenRoute.VOTE.route
                            )
                        },
                        navigationMyPage = {
                            navController.navigate(GroupDetailScreenRoute.VOTE_DETAIL.route) // 수정 필요
                        },
                    )
                }

                composable(route = GroupDetailScreenRoute.LIST.route
                    .plus("/{$KEY_GROUP_ID}")
                    .plus("/{$KEY_MEMBER_ID}")
                    .plus("/{$KEY_IS_AGENDA}"),
                    arguments = listOf(
                        navArgument(KEY_GROUP_ID) { type = NavType.LongType },
                        navArgument(KEY_MEMBER_ID) { type = NavType.LongType },
                        navArgument(KEY_IS_AGENDA) { type = NavType.BoolType }
                    )
                ) {
                    PhotoListScreen(
                        navigationBack = {
                            navController.popBackStack()
                        },
                        navigationAgenda = {
                            navController.previousBackStackEntry?.savedStateHandle?.set("agendaData", it)
                            navController.popBackStack()
                        }
                    )
                }

                composable(route = GroupDetailScreenRoute.VOTE.route) {
                    VoteScreen1(
                        navigationBack = {
                            navController.popBackStack()
                        }, navigationAgenda = {
                            navController.navigate(
                                GroupDetailScreenRoute.AGENDA.route
                            )
                        }
                    )
                }

                composable(route = GroupDetailScreenRoute.AGENDA.route) {
                    AgendaScreen(
                        navController = navController,
                        navigationBack = {
                            navController.popBackStack()
                        }, navigationPhotoList = { groupId, memberId ->
                            navController.navigate(
                                GroupDetailScreenRoute.LIST.route.plus("/${groupId}")
                                    .plus("/${memberId}").plus("/${true}")
                            )
                        }
                    )
                }

                composable(route = GroupDetailScreenRoute.VOTE_DETAIL.route) {
                    VoteDetailScreen(
                        navigationBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }

    }

}

enum class GroupDetailScreenRoute(val route: String) {
    DETAIL("detail"),
    LIST("list"),
    VOTE("vote"),
    AGENDA("agenda"),
    VOTE_DETAIL("vote-detail"),
}

const val KEY_GROUP_ID = "group-id"
const val KEY_MEMBER_ID = "member-id"
const val KEY_AGENDA_ID = "agenda-id"
const val KEY_IS_AGENDA = "is-agenda"
