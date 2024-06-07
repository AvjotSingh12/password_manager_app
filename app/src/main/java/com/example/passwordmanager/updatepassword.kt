package com.example.passwordmanager



import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpdatePasswordScreen(navController: NavController, service: String) {
    val context = LocalContext.current
    val dbHandler = DBHandler(context)
    val password = dbHandler.getPassword(service)
    val passwordList: List<PasswordModel> = dbHandler.readPasswords() ?: emptyList()

    val serviceName = remember { mutableStateOf(TextFieldValue(password?.service ?: "")) }
    val username = remember { mutableStateOf(TextFieldValue(password?.username ?: "")) }
    val passwordValue = remember { mutableStateOf(TextFieldValue(password?.passsword ?: "")) }





        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Update Service",
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
            value = passwordValue.value,
            onValueChange = { passwordValue.value = it },
            placeholder = { Text(text = "Enter Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                dbHandler.updatePassword(
                    serviceName.value.text,
                    username.value.text,
                    passwordValue.value.text
                )
                Toast.makeText(context, "Service Updated in Database", Toast.LENGTH_SHORT).show()
                navController.navigate("passwordList")
            }) {
                Text(text = "Update", color = Color.White)
            }

            Button(onClick = {
                dbHandler.deletePassword(service)
                Toast.makeText(context, service, Toast.LENGTH_SHORT).show()
                navController.navigate("passwordList")
            }) {
                Text("Delete")
            }
        }
    }
}
