package com.example.myapplication.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.Screen

/*
Creating a login page with logo, text fields for username and password input,
and a login button
 */
@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current
    var username by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(
            modifier = Modifier.padding(10.dp),
            painter = painterResource(id = R.drawable.littlelemonlogo),
            contentDescription = "Logo Image")

        TextField(
            modifier = Modifier.padding(10.dp),
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text(text = "Username")}
        )

        TextField(
            modifier = Modifier.padding(10.dp),
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password")}
        )

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                Log.d("AAA", username.text)
                Log.d("AAA", password.text)
                if(username.text == "Paul" && password.text == "260220")
            {
                Toast.makeText(context, "Welcome to Little Lemon!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Home.route)
            } else {
                Toast.makeText(context, "Invalid credentials." + "Please try again.", Toast.LENGTH_LONG).show()
                    username = TextFieldValue()
                    password = TextFieldValue()
            } },
            colors = ButtonDefaults.buttonColors(Color(0xFF495E57))
        ) {
            Text(text = stringResource(id = R.string.login_button), color = Color(0xFFEDEFEE))
        }
    }
}