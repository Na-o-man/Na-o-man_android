package com.hgh.na_o_man.presentation.ui.add

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
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersNameScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersSpace
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptCheckScreen
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptScreen

@Composable
fun AddJoinHostScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "AddJoinHostScreen")
    Scaffold(
        containerColor = lightSkyBlue
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = "members_name_screen"
            ) {
                composable("members_name_screen") {
                    MembersNameScreen(viewModel, navController)
                }
                composable("members_adjective") {
                    MembersAdjective(viewModel, navController)
                }
                composable("members_space") {
                    MembersSpace(viewModel, navController)
                }
                composable("members_loading") {
                    MembersLoading(viewModel, navController)
                }
                composable("members_folder") {
                    MembersFolder(viewModel, navController)
                }
                composable("accept_invite") {
                    AcceptScreen(viewModel, navController)
                }
                composable("accept_check_screen") {
                    AcceptCheckScreen(viewModel, navController)
                }
            }
        }
    }
}


