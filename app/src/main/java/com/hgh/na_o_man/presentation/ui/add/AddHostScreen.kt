package com.hgh.na_o_man.presentation.ui.add

import MembersNameScreen
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.lightSkyBlue
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersAdjective
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersFolder
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersLoading
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersSpace

@Composable
fun AddHostScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "AddHostScreen")
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
                    MembersNameScreen(viewModel, navController)
                }
                composable(route = AddScreenRoute.ADJECTIVE.route) {
                    MembersAdjective(viewModel, navController)
                }
                composable(route = AddScreenRoute.SPACEINPUT.route) {
                    MembersSpace(viewModel, navController)
                }
                composable(route = AddScreenRoute._LOADING.route) {
                    MembersLoading(viewModel, navController)
                }
                composable(route = AddScreenRoute.FOLDER.route) {
                    MembersFolder(viewModel, navController)
                }
            }
        }
    }
}

enum class AddScreenRoute(val route: String){
    NAMEINPUT("members_name_screen"),
    ADJECTIVE("members_adjective_screen"),
    SPACEINPUT("members_space_screen"),
    _LOADING("members_loading"),
    FOLDER("members_folder")
}

