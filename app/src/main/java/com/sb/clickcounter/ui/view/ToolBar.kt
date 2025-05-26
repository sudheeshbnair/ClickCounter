package com.sb.clickcounter.ui.view

import android.content.res.Resources
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.sb.clickcounter.R


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