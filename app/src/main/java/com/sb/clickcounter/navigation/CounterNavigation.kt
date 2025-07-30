package com.sb.clickcounter.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sb.clickcounter.ui.view.CounterView
import com.sb.clickcounter.ui.view.NavigationDrawer
import com.sb.clickcounter.ui.view.ToolBar

@Composable
fun CounterNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home> {
            val snackbarHostState = remember { SnackbarHostState() }
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            NavigationDrawer(drawerState) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { ToolBar(drawerState) },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    CounterView(innerPadding, navController, snackbarHostState)
                }
            }
        }
        settingsNavigationGraph(navController)
    }
}