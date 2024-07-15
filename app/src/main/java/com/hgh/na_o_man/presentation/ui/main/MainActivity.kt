package com.hgh.na_o_man.presentation.ui.main

import android.content.Intent
import android.os.Bundle
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

}