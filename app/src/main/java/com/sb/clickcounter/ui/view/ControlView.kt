package com.sb.clickcounter.ui.view

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sb.clickcounter.R


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