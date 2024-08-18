package com.hgh.na_o_man.presentation.ui.add

import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersNameScreen
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.*
import com.hgh.na_o_man.presentation.ui.main.MainScreenRoute

@Composable
fun AddHostScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Log.d("리컴포저블", "AddHostScreen")

    val context = LocalContext.current as Activity

    Scaffold(
        containerColor = lightSkyBlue
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = AddScreenRoute.NAMEINPUT.route
            ) {
                composable(route = AddScreenRoute.NAMEINPUT.route) {
                    MembersNameScreen(
                        viewModel = viewModel,
                        navController = navController,
                        navigationHome = {
                            context.finish()
                        }
                    )
                }
                composable(route = AddScreenRoute.ADJECTIVE.route) {
                    MembersAdjective(
                        viewModel = viewModel,
                        navController = navController,
                        navigationBack = {
                            navController.navigate(AddScreenRoute.NAMEINPUT.route)
                        },
                        navigationMyPage = {
                            navController.navigate(MainScreenRoute.MY_PAGE.route)
                        }
                    )
                }
                composable(route = AddScreenRoute.SPACEINPUT.route) {
                    MembersSpace(
                        viewModel = viewModel,
                        navController = navController,
                        navigationBack = {
                            navController.navigate(AddScreenRoute.ADJECTIVE.route)
                        }
                    )
                }
                composable(route = AddScreenRoute.LOADING.route) {
                    MembersLoading(viewModel = viewModel, navController = navController)
                }
                composable(route = AddScreenRoute.FOLDER.route) {
                    MembersFolder(viewModel = viewModel, navigationHome = {
                        context.finish()
                    })
                }
            }
        }
    }
}


enum class AddScreenRoute(val route: String){
    NAMEINPUT("members_name_screen"),
    ADJECTIVE("members_adjective_screen"),
    SPACEINPUT("members_space_screen"),
    LOADING("members_loading"),
    FOLDER("members_folder")
}

