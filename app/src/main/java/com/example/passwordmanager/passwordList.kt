package com.example.passwordmanager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.content.Context

import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton

import androidx.compose.material3.Text

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PasswordListScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp))  {

        Text(
            text = "Password Manager",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PasswordList(LocalContext.current, navController)
    }


    Column(modifier = Modifier.padding(16.dp)) {


        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.BottomEnd

        ) {
            FloatingActionButton(
                onClick = { navController.navigate("addnewpassword") },
                shape = RoundedCornerShape(16.dp),

                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Password",
                    tint = Color.White
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PasswordList(context: Context, navController: NavController) {
    val dbHandler = DBHandler(context)
    val passwordList: List<PasswordModel> = dbHandler.readPasswords() ?: emptyList()

    LazyColumn {
        itemsIndexed(passwordList) { index, item ->
            PasswordListItem(
                passwordModel = item,
                onDelete = {
                    dbHandler.deletePassword(item.username)
                    Toast.makeText(context, "Password Deleted", Toast.LENGTH_SHORT).show()
                    navController.navigate("passwordList") // Refresh list
                },
                onUpdate = {
                    navController.navigate("updatePassword/${item.service}")
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PasswordListItem(passwordModel: PasswordModel, onDelete: () -> Unit, onUpdate: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onUpdate),
        shape = RoundedCornerShape(12.dp),


         // Set the background color to white

    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
             horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center){


            Text(
                text = passwordModel.service,
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(5.dp))



        }
    }
}

