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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

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
                CountText(count)
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
                CountText(count, Modifier.fillMaxWidth(0.45f))
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