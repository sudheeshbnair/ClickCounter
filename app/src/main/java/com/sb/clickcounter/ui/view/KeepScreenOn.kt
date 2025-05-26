package com.sb.clickcounter.ui.view

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun KeepScreenOn() = AndroidView({ View(it).apply { keepScreenOn = true } })