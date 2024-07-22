package com.hgh.na_o_man.presentation.ui.add

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptInviteScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.MembersInviteScreen
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptScreen
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptWhoScreen1
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptWhoScreen2
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptWhoScreen3
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Modifier

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

            composable("membersInvite") {
                MembersInviteScreen(navController)
            }
            composable("acceptWho") { // 새로운 목적지 추가
                AcceptScreen() // AcceptWhoScreen 호출
            }
        }
    }

    private fun closeActivity() {
        finish()
    }

    companion object {
        const val ADD_GROUP = "addGroup"
    }
}
