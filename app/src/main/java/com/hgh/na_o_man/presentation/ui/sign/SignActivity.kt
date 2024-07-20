package com.hgh.na_o_man.presentation.ui.sign

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.hgh.na_o_man.presentation.Utill.SocialLoginUtil
import com.hgh.na_o_man.presentation.theme.NaOManTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignActivity : ComponentActivity() {

    private var googleLoginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                socialLoginUtil.handleGoogleSignInResult(task)
            }
        }

    private lateinit var socialLoginUtil: SocialLoginUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NaOManTheme {
                SignScreen()
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