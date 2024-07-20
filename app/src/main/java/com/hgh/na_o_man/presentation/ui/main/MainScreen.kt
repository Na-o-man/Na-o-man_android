package com.hgh.na_o_man.presentation.ui.main

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hgh.na_o_man.R
import com.hgh.na_o_man.presentation.theme.Purple40
import com.hgh.na_o_man.presentation.theme.Purple80
import com.hgh.na_o_man.presentation.ui.main.alarm.AlarmScreen
import com.hgh.na_o_man.presentation.ui.main.home.HomeScreen
import com.hgh.na_o_man.presentation.ui.main.mypage.MyPageScreen

@Composable
fun MainScreen(
    intentToAccept: () -> Unit,
    intentToRequest: () -> Unit,
    navController: NavHostController = rememberNavController(),
) {
    var bottomBarState by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val coroutineScope = rememberCoroutineScope()
    Log.d("리컴포저블", "MainScreen")

    bottomBarState = when (currentDestination?.route) {
        MainScreenRoute.HOME.route, MainScreenRoute.ALARM.route, MainScreenRoute.ADD.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            if (bottomBarState) {
                BottomNavigation(
                    currentDestination = currentDestination,
                    navigateToScreen = { navigationItem ->
                        navigateBottomNavigationScreen(
                            navController = navController,
                            navigationItem = navigationItem,
                        )
                    }
                )
            }
        },
        containerColor = Color(0xFFBBCFE5)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = MainScreenRoute.HOME.route
            ) {
                composable(route = MainScreenRoute.HOME.route) {
                    HomeScreen(
                        navigationMyPage = {
                            navController.navigate(MainScreenRoute.MY_PAGE.route)
                        }
                    )
                }

                composable(route = MainScreenRoute.ADD.route) {
                }

                composable(route = MainScreenRoute.ALARM.route) {
                    AlarmScreen()
                }
                composable(route = MainScreenRoute.MY_PAGE.route) {
                    MyPageScreen(
                        navigationBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun BottomNavigation(
    currentDestination: NavDestination?,
    navigateToScreen: (BottomNavigationItem) -> Unit,
) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4A90E2),
            Color(0xFF50E3C2),
            Color(0xFF4A90E2),
            Color(0xFF50E3C2),
        ),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    Surface(
        color = Color(0xFF4A90E2),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .padding(bottom = 21.dp)
            .height(90.dp)
            .border(4.dp, gradient, RoundedCornerShape(50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavigationItem.entries.forEach { navigationItem ->
                IconButton(
                    onClick = { navigateToScreen(navigationItem) },
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                        )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navigationItem.icon),
                        contentDescription = null,
                        tint = if (currentDestination?.route == navigationItem.route) Purple80 else Purple40,
                    )
                }
            }
        }
    }
}

fun navigateBottomNavigationScreen(
    navController: NavHostController,
    navigationItem: BottomNavigationItem,
) {

    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}


enum class BottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    // @StringRes val title: Int,
) {
    HOME(
        route = MainScreenRoute.HOME.route,
        icon = R.drawable.ic_launcher_foreground
    ),
    ADD(
        route = MainScreenRoute.ADD.route,
        icon = R.drawable.ic_launcher_foreground,
    ),
    ALARM(
        route = MainScreenRoute.ALARM.route,
        icon = R.drawable.ic_launcher_foreground,
    )
}

enum class MainScreenRoute(val route: String) {
    HOME("home"),
    ADD("add_group"),
    ALARM("alarm"),
    MY_PAGE("my_page"),
}

//@Preview
//@Composable
//fun bottomNavPreview() {
//    TtoklipBottomNavigation(  { navigationItem ->
//        navigateBottomNavigationScreen()
//    })
//}

