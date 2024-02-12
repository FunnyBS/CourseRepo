@file:OptIn(DelicateCoroutinesApi::class)

package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Coursera"

@Composable
fun DishItem(text: String) {
    Text(text = text, modifier = Modifier.fillMaxWidth())
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoList() {
    val todosState = remember { mutableStateOf("") }

    LaunchedEffect(LocalLifecycleOwner.current.lifecycleScope) {
        val response = try {
            RetrofitInstance.api.getDishes()
        } catch (e: IOException) {
            Log.e(TAG, "IOException, you might not have internet connection")
            return@LaunchedEffect
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException, unexpected response")
            return@LaunchedEffect
        }

        if (response.isSuccessful && response.body() != null) {
            todosState.value = response.body().toString()!!
            Log.d(TAG, "Successful")
        } else {
            Log.e(TAG, "Response not successful")
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = true
    ) {
        items(1) {
            todosState.value.forEach { todo ->

                DishItem(text = todo.toString())
            }
        }
    }
}

@Composable
fun MyApp() {
    TodoList()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}