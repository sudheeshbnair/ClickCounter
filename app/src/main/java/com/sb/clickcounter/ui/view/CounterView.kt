package com.sb.clickcounter.ui.view

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sb.clickcounter.R

@Composable
fun CounterView(resources: Resources, innerPadding: PaddingValues) {
    var count by rememberSaveable { mutableIntStateOf(0) }

    val plusClick = { if (count < 9999) count++ }
    val minusClick = { if (count > 0) count-- }
    val resetClick = { count = 0 }

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.fillMaxHeight(0.2f))
                CounterText(count)
                Spacer(Modifier.fillMaxHeight(0.3f))
                ControlView(resources, plusClick, minusClick, resetClick)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = innerPadding.calculateStartPadding(
                            LayoutDirection.Ltr
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CounterText(count, Modifier.fillMaxWidth(0.45f))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(1f)
                        .fillMaxHeight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ControlView(resources, plusClick, minusClick, resetClick)
                }

            }
        }
    }
}

@Composable
fun CounterText(count: Int, modifier: Modifier = Modifier) {
    Text(
        count.toString(),
        modifier = modifier.padding(horizontal = 16.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 100.sp,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

@Composable
fun ControlView(
    resources: Resources,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            ElevatedButton(
                modifier = Modifier.size(100.dp),
                onClick = onMinusClick
            ) {
                Text(
                    resources.getString(R.string.minus),
                    fontSize = 50.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(Modifier.width(50.dp))
            ElevatedButton(
                modifier = Modifier.size(100.dp),
                onClick = onPlusClick
            ) {
                Text(
                    resources.getString(R.string.plus),
                    fontSize = 50.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        TextButton(
            modifier = Modifier.padding(top = 32.dp),
            onClick = onResetClick
        ) {
            Text(
                resources.getString(R.string.reset),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}