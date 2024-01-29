package com.example.drugsproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drugsproject.MusicPlayer.MusicPlayerComposable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Other.route) {
            OtherScreen(navController)
        }
        composable(route = BottomBarScreen.HabitTracker.route) {
            NonSmokingHabitApp()
        }
        composable(route = "music") {
            MusicPlayerComposable()
        }
    }
}