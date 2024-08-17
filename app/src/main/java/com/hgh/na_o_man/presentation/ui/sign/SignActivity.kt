package com.hgh.na_o_man.presentation.ui.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignActivity : ComponentActivity() {
    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaOManTheme {
                SignHostScreen(dataStoreUtil = dataStoreUtil)
            }
        }
    }

    private fun closeActivity() {
        finish()
    }

    companion object {
        const val SIGN_ACTIVITY = "sign"

        fun goAuth(context: Context) {
            val intent = Intent(context, SignActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }

        fun intentUpload(context: Context) =
            Intent(context, SignActivity::class.java).apply {
                putExtra(SIGN_ACTIVITY, true)
            }
    }
}