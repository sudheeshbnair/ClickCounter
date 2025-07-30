package com.sb.clickcounter.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sb.clickcounter.R
import com.sb.clickcounter.ui.view.SettingsView
import com.sb.clickcounter.ui.view.ToolBar
import kotlinx.serialization.Serializable


@Serializable
data object SettingsNavigationGraph

fun NavGraphBuilder.settingsNavigationGraph(navController: NavController) {

    navigation<SettingsNavigationGraph>(startDestination = Screen.Settings) {

        composable<Screen.Settings> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { ToolBar(screenTitle = stringResource(R.string.settings)) }
            ) { innerPadding ->
                SettingsView(innerPadding)
            }
        }
    }
}