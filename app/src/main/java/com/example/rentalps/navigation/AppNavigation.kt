package com.example.rentalps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.rentalps.screen.BookingScreen
import com.example.rentalps.screen.HistoryScreen
import com.example.rentalps.screen.HomeScreen
import com.example.rentalps.screen.LoginScreen
import com.example.rentalps.screen.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLogin = { navController.navigate("home") },
                onRegister = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(onRegister = { navController.navigate("login") })
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable(
            "booking/{title}/{price}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            BookingScreen(navController, title, price)
        }

        composable("history") {
            HistoryScreen()
        }
    }
}