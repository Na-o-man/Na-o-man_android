package com.hgh.na_o_man.presentation.ui.add

import android.graphics.Paint.Join
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
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinViewModel

@Composable
fun JoinHostScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Log.d("리컴포저블", "JoinHostScreen")
    Scaffold(
        containerColor = lightSkyBlue
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = JoinScreenRoute.INVITE.route
            ) {
                composable(route = JoinScreenRoute.INVITE.route) {
                    AcceptScreen(viewModel, navController)
                }
                composable(route = JoinScreenRoute.CHECK.route) {
                    AcceptCheckScreen(viewModel, navController)
                }
            }
        }
    }
}

enum class JoinScreenRoute(val route: String){
    INVITE("accept_invite_screen"),
    CHECK("accept_check_screen")
}

