package com.hgh.na_o_man.presentation.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
                val navController = rememberNavController()

                val addViewModel: AddViewModel = hiltViewModel()
                val joinViewModel: JoinViewModel = hiltViewModel()

                val startDestination = remember {
                    determineStartDestination()
                }

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable("add_host") { AddHostScreen() }
                    composable("join_host") { JoinHostScreen() }
                }

            }
        }
    }

    private fun determineStartDestination(): String {
        val isJoin = intent.getBooleanExtra(ADD_GROUP, false)

        return if (isJoin) "join_host" else "add_host"
    }

    companion object {
        const val ADD_GROUP = "addGroup"

        fun newIntent(context: Context, isJoin: Boolean) =
            Intent(context, AddGroupActivity::class.java).apply {
                putExtra(ADD_GROUP, isJoin)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AcceptScreen", "Activity is destroyed")
    }
}
