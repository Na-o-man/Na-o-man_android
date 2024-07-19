package com.hgh.na_o_man.presentation.ui.add

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptInviteScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersInviteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddGroupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaOManTheme {
                // NavController 생성
                val navController = rememberNavController()

                // NavHost 설정
                MyAppNavHost(navController)
            }
        }
    }

    // NavHost에서 사용할 NavHost 설정
    @Composable
    fun MyAppNavHost(navController: NavController) {
        NavHost(navController as NavHostController, startDestination = if (intent.getBooleanExtra(ADD_GROUP, true)) "acceptInvite" else "membersInvite") {
            composable("acceptInvite") {
                AcceptInviteScreen(navController)
            }
            composable("membersInvite") {
                MembersInviteScreen(navController)
            }
            // 여기에 추가적인 화면을 더할 수 있습니다.
        }
    }

    private fun closeActivity() {
        finish()
    }

    companion object {
        const val ADD_GROUP = "addGroup"
    }
}
