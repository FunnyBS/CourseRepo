package com.example.myapplication.data

interface Destinations {
    val route:String
}

object HomeCourse: Destinations {
    override val route = "Home"
}

object MenuListCourse: Destinations {
    override val route = "MenuList"
}

object LoginScreen: Destinations {
    override val route = "LoginScreen"
}

object HomeScreen: Destinations {
    override val route = "HomeScreen"
}

object Dashboard: Destinations {
    override val route = "HomeCourse"
}

object Details: Destinations {
    override val route = "DetailsCourse"
}

object Menu: Destinations {
    const val argOrderNo = "OrderNo"
    override val route = "Menu"
}
