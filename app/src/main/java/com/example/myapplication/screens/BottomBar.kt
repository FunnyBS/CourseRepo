package com.example.myapplication.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.myapplication.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(selectedIndex: MutableState<Int>, navController: NavController){

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
    NavigationBar {
        bottomBarItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(item.title)
                    },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null && index != selectedIndex.value){
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews && index != selectedIndex.value) {
                                Badge ()
                            }
                        }) {
                        Icon(
                            imageVector = if (index == selectedIndex.value){
                                item.selectedIcon
                            }else {
                                item.selectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                })
        }
    }
}