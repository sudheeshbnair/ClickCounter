package com.sb.clickcounter

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sb.clickcounter.ui.theme.ClickCounterTheme

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

@Composable
fun CounterView(resources: Resources, innerPadding: PaddingValues) {
    var count by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.fillMaxHeight(0.2f))
        Text(
            count.toString(),
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 100.sp,
            maxLines = 1,
            minLines = 1
        )
        Spacer(Modifier.fillMaxHeight(0.3f))
        Row {
            ElevatedButton(modifier = Modifier.size(100.dp), onClick = { if (count > 0) count-- }) {
                Text(
                    resources.getString(R.string.minus),
                    fontSize = 50.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(Modifier.width(50.dp))
            ElevatedButton(
                modifier = Modifier.size(100.dp),
                onClick = { if (count < 9999) count++ }) {
                Text(
                    resources.getString(R.string.plus),
                    fontSize = 50.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = { count = 0 }
        ) {
            Text(resources.getString(R.string.reset), fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClickCounterTheme {
        // Greeting("Android")
    }
}