package com.hgh.na_o_man.presentation.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel
import com.hgh.na_o_man.presentation.ui.add.joingroup.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddGroupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaOManTheme {
                // NavController 생성
                val navController = rememberNavController()

                // ViewModel 주입
                val addViewModel: AddViewModel = hiltViewModel()
                val joinViewModel: JoinViewModel = hiltViewModel()

                // Determine the start destination based on the intent's extra value
                val startDestination = remember {
                    determineStartDestination()
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    // Define your navigation graph here
                    composable("add_host") { AddHostScreen() }
                    composable("join_host") { JoinHostScreen() }
                }
            }
        }
    }

    private fun determineStartDestination(): String {
        val isJoin = intent.getBooleanExtra(JOIN_GROUP, false)
        return if (isJoin) "add_host" else "join_host"
    }

    companion object {
        const val JOIN_GROUP = "joinGroup"
        const val ADD_GROUP = "addGroup"

        fun newIntent(context: Context, isJoin: Boolean) =
            Intent(context, AddGroupActivity::class.java).apply {
                putExtra(JOIN_GROUP, isJoin)
                putExtra(ADD_GROUP, isJoin)
            }
    }
}
