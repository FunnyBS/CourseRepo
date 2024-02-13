@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.DishDetails
import com.example.myapplication.R
import com.example.myapplication.data.Dashboard
import com.example.myapplication.data.Details
import com.example.myapplication.data.Menu
import com.example.myapplication.data.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun MyNavCourseFF () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Dashboard.route) {
        composable(Dashboard.route) {
//            DashboardScreenCourse(navController)
        }
        composable(Details.route) {
//            DetailsScreenCourse()
        }
        composable(
            Menu.route + "/{${Menu.argOrderNo}}",
            arguments = listOf(
                navArgument(Menu.argOrderNo) { type = NavType.IntType}
            )) {
//            MenuScreen(it.arguments?.getInt(Menu.argOrderNo))
        }
    }
}

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false,
    var badgeCount: Int? = null)

sealed class Screen(val route:String) {
    object Login: Screen("Login")
    object Home: Screen("Home")
    object News: Screen("News")
    object Settings: Screen("Settings")
    object DishDetails: Screen("DishDetails")
}

private fun currentDestination(navController: NavController): String? {
    return navController.currentBackStackEntry?.destination?.route
}

@Composable
fun MyNav() {
    val navController = rememberNavController()
    val selectedIndex = rememberSaveable {
        mutableStateOf(0)
    }
    val bottomBarItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "News",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true
        )
    )
    val topBarItems = listOf(
        NavigationItem(
            title = "Menu",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Urgent",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()

    Scaffold(
            bottomBar = {
                if (bottomBarItems.any { it.title == currentDestination(navController) }) {
                    BottomBar(navController = navController, selectedIndex = selectedIndex)
                }
            },
            topBar = {
                if (bottomBarItems.any { it.title == currentDestination(navController) }){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        } ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu Button",
                                Modifier.size(24.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                            contentDescription = "Little Lemon Logo",
                            modifier = Modifier
                                .fillMaxWidth(0.85F)
                                .padding(horizontal = 20.dp)
                                .height(40.dp)
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_basket),
                                contentDescription = "Cart",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

            }
        ) {
            it
            NavHost(navController = navController, startDestination = Screen.Login.route) {
                composable(Screen.Login.route) {
                    LoginScreen(navController)
                }
                composable(Screen.Home.route) {
                    HomeScreen(selectedIndex, navController)
                }
                composable(Screen.News.route) {
                    News(selectedIndex, navController)
                }
                composable(Screen.Settings.route) {
                    Settings(selectedIndex, navController)
                }
                composable(Screen.DishDetails.route + "/{id}") { backStackEntry ->
                    val arguments = requireNotNull(backStackEntry.arguments)
                    val dishId = arguments.getInt("id")
                    DishDetails(dishId, selectedIndex, navController)
                }
            }
        }
    }



