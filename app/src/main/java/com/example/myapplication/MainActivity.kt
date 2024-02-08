@file:OptIn(DelicateCoroutinesApi::class)

package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val TAG = "Coursera"

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyNav()
            GlobalScope.launch {
                delay(4000L)
                Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
            }
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
    }
}

