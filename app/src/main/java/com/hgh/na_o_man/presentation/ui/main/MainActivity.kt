package com.hgh.na_o_man.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import com.hgh.na_o_man.presentation.ui.add.AddGroupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private var createResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ ->
        Log.d("한건희","createResultLauncher")
        viewModel.setEvent(MainContract.MainEvent.FinishedCreateActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NaOManTheme {
                MainScreen({
                    intentToAddedGroup(true)
                }, {
                    intentToAddedGroup(false)
                })
            }
        }
    }

    private fun intentToAddedGroup(isAccept: Boolean) {
        val intent = Intent(this, AddGroupActivity::class.java).apply {
            putExtra(AddGroupActivity.ADD_GROUP, isAccept)
        }
        createResultLauncher.launch(intent)
    }

    companion object {
        fun goMain(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}