package com.hgh.na_o_man.presentation.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.addgroup.AddViewModel
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

                // NavHost 설정
                AddJoinHostScreen()
            }
        }
    }

    private fun closeActivity() {
        finish()
    }

    companion object {
        const val ADD_GROUP = "addGroup"

        fun newIntent(context: Context, isJoin: Boolean) =
            Intent(context, AddGroupActivity::class.java).apply {
                putExtra(ADD_GROUP, isJoin)
            }
    }
}
