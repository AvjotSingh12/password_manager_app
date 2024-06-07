package com.example.passwordmanager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PasswordManagerApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "passwordList") {
        composable("passwordList") {
            PasswordListScreen(navController)
        }
        composable("addnewpassword") {
            AddNewPasswordScreen(navController)
        }

    }
}
