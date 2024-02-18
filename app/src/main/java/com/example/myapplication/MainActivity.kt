@file:OptIn(DelicateCoroutinesApi::class, ExperimentalGlideComposeApi::class)
@file:Suppress("UNCHECKED_CAST")

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.course.MenuCoursera
import com.example.myapplication.models.ContactsViewModel
import com.example.myapplication.screens.SecondActivity
import com.example.myapplication.ui.theme.LittleLemonTheme
import com.example.myapplication.utils.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Coursera"



class MainActivity : ComponentActivity() {
    private var fact = mutableStateOf(MenuCoursera())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val viewModel = viewModel<ContactsViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return ContactsViewModel() as T
                        }
                    }
                )

            Surface (
                modifier = Modifier.fillMaxSize(),
                color = viewModel.backgroundColor
            ) {
//                sendRequest()
//                MyUI(fact = fact, onImageClick = { viewModel.changeBackgroundColor() })
                Button(onClick = {
//                    Intent(applicationContext, SecondActivity::class.java).also { startActivity(it) }
                })
                {
                    Text(text = "Move to another activity")
                }
            }
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
fun ImageShowcase (onImageClick: () -> Unit){
    GlideImage(modifier = Modifier.clickable { onImageClick() }, model = "data:image/jpeg;base64,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", contentDescription = "")
}

@Composable
fun MyUI(fact: MutableState<MenuCoursera>, modifier: Modifier = Modifier, onImageClick: () -> Unit) {
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
        ImageShowcase(onImageClick = onImageClick)
        Button(onClick = { onImageClick() }) {
            Text(text = "Change Background")
        }
    }
}
