package com.sb.clickcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sb.clickcounter.ui.theme.ClickCounterTheme
import com.sb.clickcounter.ui.view.CounterView
import com.sb.clickcounter.ui.view.ToolBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { ToolBar(resources) }
                ) { innerPadding ->
                    CounterView(resources, innerPadding)
                }
            }
        }
    }
}

