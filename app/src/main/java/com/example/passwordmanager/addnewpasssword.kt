package com.example.passwordmanager

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddNewPasswordScreen(navController: NavController) {
    AddDataToDatabase(navController, LocalContext.current)
}

@Composable
fun AddDataToDatabase(navController: NavController, context: Context) {
    val serviceName = remember { mutableStateOf(TextFieldValue()) }
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val dbHandler = DBHandler(context)

        Text(
            text = "Add New Service",
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = serviceName.value,
            onValueChange = { serviceName.value = it },
            placeholder = { Text(text = "Enter Service") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            placeholder = { Text(text = "Enter Username") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            placeholder = { Text(text = "Enter Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (serviceName.value.text.isBlank() || username.value.text.isBlank() || password.value.text.isBlank()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                dbHandler.addNewPassword(
                    serviceName.value.text,
                    username.value.text,
                    password.value.text
                )
                Toast.makeText(context, "Service Added to Database", Toast.LENGTH_SHORT).show()
                navController.navigate("passwordList")
            }
        }) {
            Text(text = "Add Service to Database", color = Color.White)
        }
    }
}
