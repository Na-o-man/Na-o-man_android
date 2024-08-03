package com.hgh.na_o_man.presentation.ui.detail.vote

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class VoteActivity {
    @Composable
    fun App() {
        val navController = rememberNavController() // NavController 초기화

        NavHost(navController, startDestination = "votescreen1") {
            composable("votescreen1") { VoteScreen1(navController) }
            composable("votescreen2") { VoteScreen2(navController) }
        }
    }
}