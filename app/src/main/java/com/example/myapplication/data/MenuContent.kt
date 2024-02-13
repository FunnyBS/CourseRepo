package com.example.myapplication.data

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Pink80
import com.example.myapplication.ui.theme.Purple80
import com.example.myapplication.ui.theme.PurpleGrey80

@Composable
fun MenuContent (paddingValues: PaddingValues) {
    Surface (modifier = Modifier.padding(paddingValues)) {
        val menuPadding = 8.dp
        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            ORIENTATION_LANDSCAPE -> {
                Column {
                    Row (modifier = Modifier.weight(0.5F)) {
                        Text(
                            text = "Appetizers",
                            modifier = Modifier
                                .weight(0.25F)
                                .fillMaxHeight()
                                .background(Purple80)
                                .padding(menuPadding)
                        )
                        Text(text = "Salads",
                            modifier = Modifier
                                .weight(0.25F)
                                .fillMaxHeight()
                                .padding(menuPadding))
                    }
                    Row (modifier = Modifier.weight(0.5F)) {
                        Text(text = "Drinks",
                            modifier = Modifier
                                .weight(0.25F)
                                .fillMaxHeight()
                                .background(Pink80)
                                .padding(menuPadding))
                        Text(text = "Mains",
                            modifier = Modifier
                                .weight(0.25F)
                                .fillMaxHeight()
                                .background(PurpleGrey80)
                                .padding(menuPadding))
                    }
                }
            } else -> {
                Column {
                    Text(text = "Appetizers", modifier = Modifier
                        .weight(0.25F)
                        .fillMaxWidth()
                        .background(Purple80)
                        .padding(menuPadding))
                    Text(text = "Salads", modifier = Modifier
                        .weight(0.25F)
                        .fillMaxWidth()
                        .padding(menuPadding))
                    Text(text = "Drinks", modifier = Modifier
                        .weight(0.25F)
                        .fillMaxWidth()
                        .background(Pink80)
                        .padding(menuPadding))
                    Text(text = "Mains", modifier = Modifier
                        .weight(0.25F)
                        .fillMaxWidth()
                        .background(PurpleGrey80)
                        .padding(menuPadding))
                }
            }
        }
    }
}

