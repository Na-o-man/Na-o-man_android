package com.hgh.na_o_man.presentation.ui.add

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.joingroup.AcceptInviteScreen
import com.hgh.na_o_man.presentation.ui.add.addgroup.RequestInviteScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddGroupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            NaOManTheme {
                if (intent.getBooleanExtra(ADD_GROUP, true)) {
                    AcceptInviteScreen()
                } else {
                    RequestInviteScreen()
                }
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