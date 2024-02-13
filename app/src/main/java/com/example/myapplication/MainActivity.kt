@file:OptIn(DelicateCoroutinesApi::class)

package com.example.myapplication

import io.ktor.client.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.course.MenuCoursera
import com.example.myapplication.utils.RetrofitInstance
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import org.json.JSONObject

const val TAG = "Coursera"

class MainActivity : ComponentActivity() {
    private var fact = mutableStateOf(MenuCoursera())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                sendRequest()
                MyUI(fact = fact)
            }
        }
    }

    private fun sendRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response =
                try {
                    RetrofitInstance.api.getRandomFact()
                }catch (e: HttpException){
                    Toast.makeText(applicationContext, "http error: ${e.message}", Toast.LENGTH_SHORT).show()
                    return@launch
                }catch (e: IOException){
                    Toast.makeText(applicationContext, "app error: ${e.message}", Toast.LENGTH_SHORT).show()
                    return@launch
                }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    fact.value = response.body() !!
                }
            }
        }
    }
}

@Composable
fun MyUI(fact: MutableState<MenuCoursera>, modifier: Modifier = Modifier) {
    val menuText = fact.value?.Salads?.menu.toString() ?: "Loading..."
    val words = java.lang.String(menuText).split(", ").map { it.trim() }.toMutableList()
    for (word in words.indices) {
        if (word == 0) {
            words[word] = words[word].drop(1)
        }
        else if (word == words.lastIndex) {
            words[word] = words[word].dropLast(1)
        }

    }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        words.forEach {word->
            Text(text = word, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 40.sp)
        }
    }
}
