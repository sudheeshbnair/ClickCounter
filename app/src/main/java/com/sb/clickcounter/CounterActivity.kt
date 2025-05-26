package com.sb.clickcounter

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.sb.clickcounter.ui.theme.ClickCounterTheme
import com.sb.clickcounter.ui.view.CounterView

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(resources: Resources) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                resources.getString(R.string.counter),
                fontWeight = FontWeight.Bold
            )
        }
    )
}