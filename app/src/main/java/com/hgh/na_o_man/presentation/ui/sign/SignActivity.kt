package com.hgh.na_o_man.presentation.ui.sign

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hgh.na_o_man.di.util.DataStoreUtil
import com.hgh.na_o_man.presentation.util.SocialLoginUtil
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
        const val SIGN = "sign"

        fun goAuth(context: Context) {
            val intent = Intent(context, SignActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}