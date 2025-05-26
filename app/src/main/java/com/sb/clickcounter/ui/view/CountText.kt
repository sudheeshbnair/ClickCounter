package com.sb.clickcounter.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CountText(count: Int, modifier: Modifier = Modifier) {
    Text(
        count.toString(),
        modifier = modifier.padding(horizontal = 16.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 100.sp,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}