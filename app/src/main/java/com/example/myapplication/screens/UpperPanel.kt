package com.example.myapplication.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LittleLemonColor

@Composable
fun UpperPanel() {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.NAME),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )

        Text(
            text = stringResource(id = R.string.chicago),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.cloud,
            modifier = Modifier.padding(start = 20.dp, bottom = 12.dp)
        )
        Row (
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description_one),
                Modifier
                    .width(220.dp)
                    .padding(end = 30.dp),
                color = LittleLemonColor.cloud,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "",
                Modifier
                    .height(140.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        Button(
            onClick = {
                        Toast.makeText(context, "Order received. Thank you!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(start = 20.dp ,bottom = 20.dp, top = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0XFFF4CE14))
        ) {
            Text(text = stringResource(id = R.string.order),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}


@Preview
@Composable
fun UpperPanelPreview () {
    UpperPanel()
}