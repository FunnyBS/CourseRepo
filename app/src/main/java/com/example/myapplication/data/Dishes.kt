package com.example.myapplication.data

import androidx.annotation.DrawableRes
import com.example.myapplication.R

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    @DrawableRes val imageResource: Int
)

object DishRepository {
    val dishes = listOf(
        Dish(
            1,
            "Greek Salad",
            "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            12.99,
            R.drawable.greeksalad
        ),
        Dish(
            2,
            "Lemon Desert",
            "Traditional homemade Italian Lemon Ricotta Cake.",
            8.99,
            R.drawable.lemondessert
        ),
        Dish(
            3,
            "Bruschetta",
            "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
            4.99,
            R.drawable.bruschetta
        ),
        Dish(
            4,
            "Grilled Fish",
            "Fish marinated in fresh orange and lemon juice. Grilled with orange and lemon wedges.",
            19.99,
            R.drawable.grilledfish
        ),
        Dish(
            5,
            "Pasta",
            "Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chilli, garlic, basil & salted ricotta cheese.",
            8.99,
            R.drawable.pastafc
        ),
        Dish(
            6,
            "Lasagne",
            "Oven-baked layers of pasta stuffed with Bolognese sauce, béchamel sauce, ham, Parmesan & mozzarella cheese .",
            7.99,
            R.drawable.lasagne
        )
    )
}

fun getDish(id: Int) = DishRepository.dishes.firstOrNull { it.id == id }