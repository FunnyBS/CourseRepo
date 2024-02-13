package com.example.myapplication.course

data class MenuCoursera(
    val Appetizers: Appetizers = Appetizers(menu = listOf()),
    val Dessert: Dessert = Dessert(menu = listOf()),
    val Drinks: Drinks = Drinks(menu = listOf()),
    var Salads: Salads = Salads(menu = listOf())
)