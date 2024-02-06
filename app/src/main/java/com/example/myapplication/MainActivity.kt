package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.WorkerThread
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.Dispatchers
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import androidx.lifecycle.lifecycleScope
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect



private const val ENDPOINT = "https://10.0.2.2:3000"
private const val SALADS_URI = "/Salads"



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCompose(this)
        }
    }
}

@Composable
fun MyCompose(context: Context) {
    val data = remember { mutableStateOf("") }

    LaunchedEffect(Unit) { // Starts a coroutine when recomposed
        try {
            val result = getMenu()
            data.value = result
        } catch (e: Exception) {
            // Handle network errors here, e.g., show an error message
            Toast.makeText(context, "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("TAG", "Error: ${e.message}", e)
        }
    }

    Text(text = data.value)
}


fun getMenu(): String{
    val httpUrlConnection = URL(ENDPOINT + SALADS_URI).openConnection() as HttpURLConnection
    httpUrlConnection.apply {
        connectTimeout = 10000
        requestMethod = "GET"
        doInput = true
    }
    if (httpUrlConnection.responseCode != HttpURLConnection.HTTP_OK) {
        return "Failure"
    }
    val streamReader = InputStreamReader(httpUrlConnection.inputStream)
    var text: String = ""
    streamReader.use {
        text = it.readText()
    }
    httpUrlConnection.disconnect()
    return text
}

@WorkerThread
fun addDish () {

}