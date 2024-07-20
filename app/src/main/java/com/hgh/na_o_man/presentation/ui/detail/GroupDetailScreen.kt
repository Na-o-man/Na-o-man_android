package com.hgh.na_o_man.presentation.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.ui.detail.photo_list.PhotoListScreen

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
                startDestination = GroupDetailScreenRoute.LIST.route
            ) {

                composable(route = GroupDetailScreenRoute.LIST.route) {
                    PhotoListScreen()
                }

                composable(route = GroupDetailScreenRoute.VOTE.route) {

                }
            }
        }

    }

}

enum class GroupDetailScreenRoute(val route: String) {
    LIST("list"),
    VOTE("vote"),
}