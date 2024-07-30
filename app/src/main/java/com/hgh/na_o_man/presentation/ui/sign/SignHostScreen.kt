package com.hgh.na_o_man.presentation.ui.sign

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.di.util.data_store.DataStoreUtil
import com.hgh.na_o_man.presentation.ui.sign.signin.ArgeeScreen
import com.hgh.na_o_man.presentation.ui.sign.signin.SignScreen
import com.hgh.na_o_man.presentation.ui.sign.signin.SignViewModel
import com.hgh.na_o_man.presentation.ui.sign.signin.UploadScreen
import com.hgh.na_o_man.presentation.ui.sign.signin.UserScreen

@Composable
fun SignHostScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: SignViewModel = hiltViewModel(),
    dataStoreUtil: DataStoreUtil,
) {

    Log.d("리컴포저블", "GroupDetailScreen")
    Scaffold(
        containerColor = Color(0xFFBBCFE5)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = SignScreenRoute.SPLASH.route
            ) {

                composable(route = SignScreenRoute.SPLASH.route) {
                    SplashScreen(naviSignScreen = {
                        navController.navigate(SignScreenRoute.LOGIN.route)
                    }, dateStoreUtil = dataStoreUtil)
                }

                composable(route = SignScreenRoute.LOGIN.route) {
                    SignScreen(naviAgreeScreen = {
                        navController.navigate(SignScreenRoute.AGREE.route)
                    }, viewModel = viewModel)
                }

                composable(route = SignScreenRoute.AGREE.route) {
                    ArgeeScreen(naviUserScreen = {
                        navController.navigate(SignScreenRoute.USER.route)
                    }, viewModel = viewModel)
                }

                composable(route = SignScreenRoute.USER.route) {
                    UserScreen(naviUploadScreen = {
                        navController.navigate(SignScreenRoute.UPLOAD.route)
                    }, viewModel = viewModel)
                }

                composable(route = SignScreenRoute.UPLOAD.route) {
                    UploadScreen(
                        viewModel = viewModel
                    )

                }
            }
        }

    }

}

enum class SignScreenRoute(val route: String) {
    SPLASH("splash"),
    LOGIN("login"),
    AGREE("argee"),
    USER("user_info"),
    UPLOAD("image_upload")
}