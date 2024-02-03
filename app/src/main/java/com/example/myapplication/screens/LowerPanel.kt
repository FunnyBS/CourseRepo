package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.Categories
import com.example.myapplication.data.Dish
import com.example.myapplication.data.DishRepository
import com.example.myapplication.ui.theme.LittleLemonColor

@Composable
fun LowerPanel (navController: NavController) {
    Column {
        WeeklySpecial()
        MenuListScreen(navController = navController)
    }
}

@Composable
fun WeeklySpecial () {
    Card (modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(Color.White))
    {
        Text(
            text = stringResource(id = R.string.week_spec),
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.charcoal,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(8.dp)
            )
    }
}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(text = category, color = Color.Black)
    }
}

@Composable
fun MenuListScreen(navController: NavController) {
    Column {
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        Divider(
            modifier = Modifier.padding(8.dp),
            color = LittleLemonColor.yellow,
            thickness = 1.dp
        )
        LazyColumn {
            items(DishRepository.dishes) { Dish ->
                MenuDish(Dish, navController)
            }
        }
    }
}

@Composable
fun MenuDish(Dish: Dish, navController: NavController) {
    Card (
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier.clickable {
            navController.navigate("DishDetails/${Dish.id}")
        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Column {
                Text(text = Dish.name, color = LittleLemonColor.charcoal, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = Dish.description,
                    color = LittleLemonColor.green,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = Dish.price.toString(),
                    color = LittleLemonColor.green,
                    fontWeight = FontWeight.Bold
                    )
            }
            Image(
                painter = painterResource(id = Dish.imageResource),
                contentDescription = Dish.name,
            )
        }
    }
}