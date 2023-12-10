package com.app.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.newsapp.ui.screen.DetailScreen
import com.app.newsapp.ui.screen.HomeScreen
import com.app.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController: NavHostController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            NewsAppTheme {
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color(0xFF2c7ae8),
                        darkIcons = false
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = "Home"
                ) {
                    composable("Home") {
                        HomeScreen(navController)
                    }

                    composable("Detail") {
                        DetailScreen(navController)
                    }

                }

            }
        }
    }
}


