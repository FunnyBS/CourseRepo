package com.example.myapplication.screens

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun ItemOrder (
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    val context = LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Card (colors = CardDefaults.cardColors(Color.White)) {
            Column {
                Text(
                    text = stringResource(id = R.string.greek_salad),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    fontWeight = FontWeight.Bold
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {
                        if (count == 0) {
                            return@IconButton
                        } else {
                            onDecrement()
                        }
                    }) { //using to decrease number of dishes
                        Canvas(
                            modifier = Modifier.size(15.dp),
                            onDraw = {
                                drawLine(
                                    color = Color.Black,
                                    start = Offset(0f, size.height / 2f),
                                    end = Offset(size.width, size.height / 2f),
                                    strokeWidth = 5f
                                )
                            }
                        )
                    }

                    Text(
                        text = "$count",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )

                    IconButton(onClick = {onIncrement()}) { //using to increase number of dishes
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                }

                Button(
                    onClick =
                    { if (count == 0) {
                        Toast.makeText(context, "No Items to be Added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Successfully Added $count Items", Toast.LENGTH_SHORT).show()
                    } },
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Add")
                }
            }

        }

    }
}