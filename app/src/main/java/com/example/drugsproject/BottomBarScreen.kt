package com.example.drugsproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Other : BottomBarScreen(
        route = "other",
        title = "Other",
        icon = Icons.Default.Add
    )
    data object HabitTracker : BottomBarScreen(
        route = "habit",
        title = "Tracker",
        icon = Icons.Default.CheckCircle
    )
}