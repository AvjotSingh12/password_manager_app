package com.example.passwordmanager
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "passwordList") {
        composable("passwordList") { PasswordListScreen(navController) }
        composable("addnewpassword") { AddNewPasswordScreen(navController) }
        composable("updatePassword/{service}") { backStackEntry ->
            val service = backStackEntry.arguments?.getString("service") ?: ""
            UpdatePasswordScreen(navController, service)
        }
    }
}
