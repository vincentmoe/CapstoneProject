package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, menu: List<MenuItemRoom>) {
    val context = LocalContext.current
    val preferencesHelper = remember { PreferencesHelper(context) }
    val userEmail by remember { mutableStateOf(preferencesHelper.getData("userEmail", "")) }

    NavHost(
        navController = navController,
        startDestination = if (userEmail.isNotEmpty()) { Home.route }
        else { Onboarding.route }
    ) {

        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, menu)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}