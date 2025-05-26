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
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
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
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize().padding(horizontal = innerPadding.calculateStartPadding(
                        LayoutDirection.Ltr)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    count.toString(),
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(0.45f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 100.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    minLines = 1
                )
                Column ( modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(1f).fillMaxHeight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
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
        }
    }
}