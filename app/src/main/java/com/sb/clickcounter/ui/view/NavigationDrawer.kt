package com.sb.clickcounter.ui.view

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(drawerState: DrawerState,  content: @Composable () -> Unit) {
    // ModalNavigationDrawer
    DismissibleNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Click Counter",
                        modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),

                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()
                    val context = LocalContext.current
                    val scope = rememberCoroutineScope()
                    NavigationDrawerItem(
                        label = { Text("Privacy Policy") },
                        selected = false,
                        onClick = {
                            val url = "https://testvg.codelynks.in/privacy_policy/click_counter.html"
                            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                            context.startActivity(intent)
                            scope.launch {
                                delay(1000)
                                drawerState.close()
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            "Version : 1.0",
                            modifier = Modifier.padding(16.dp).align(Alignment.BottomCenter),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = true,
        content = content
    )
}