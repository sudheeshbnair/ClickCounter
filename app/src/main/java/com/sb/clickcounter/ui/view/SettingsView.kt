package com.sb.clickcounter.ui.view


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SettingsView(
    innerPadding: PaddingValues
) {
    KeepScreenOn()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AnimatedSearchButton(
            hints = listOf( "\"Cars\"", "\"Properties\"", "\"Mobiles\"", "\"Bikes\"", "\"Jobs\""),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // Handle click
        }
        AnimatedSearchButtonWithBlankDelay(
            hints = listOf( "\"Cars\"", "\"Properties\"", "\"Mobiles\"", "\"Bikes\"", "\"Jobs\""),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Handle click
        }
        AnimatedHintSearchTextField(
            hints = listOf( "\"Cars\"", "\"Properties\"", "\"Mobiles\"", "\"Bikes\"", "\"Jobs\""),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // Handle click
        }

    }
}

@Composable
fun AnimatedSearchButton(
    hints: List<String>,
    prefix: String = "Search ",
    displayDuration: Long = 2000L,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var currentHintIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentHintIndex) {
        delay(displayDuration)
        currentHintIndex = (currentHintIndex + 1) % hints.size
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        color = Color(0xFFF2F2F2),
        shadowElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(12.dp))

            Row {
                Text(
                    text = prefix,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                AnimatedContent(
                    targetState = hints[currentHintIndex],
                    transitionSpec = {
                        (slideInVertically(initialOffsetY = { it }) + fadeIn(animationSpec = tween(1000)))  togetherWith
                                (slideOutVertically(targetOffsetY = { -it }) + fadeOut(animationSpec = tween(1000)))
                    },
                    label = "AnimatedSearchHint"
                ) { animatedHint ->
                    Text(
                        text = animatedHint,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSearchButtonWithBlankDelay(
    hints: List<String>,
    prefix: String = "Search ",
    displayDuration: Long = 2000L,
    blankDuration: Long = 500L,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var currentHintIndex by remember { mutableIntStateOf(0) }
    var showHint by remember { mutableStateOf(true) }

    LaunchedEffect(currentHintIndex) {
        delay(displayDuration)
        showHint = false  // Exit hint
        delay(blankDuration)
        currentHintIndex = (currentHintIndex + 1) % hints.size
        showHint = true   // Show new hint
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        color = Color(0xFFF2F2F2),
        shadowElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),

        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = prefix,
                    color = Color.Gray,
                    fontSize = 16.sp,
                )

                AnimatedContent(
                    targetState = showHint to currentHintIndex,
                    transitionSpec = {
                        val slideIn = slideInVertically(
                            animationSpec = tween(durationMillis = 600), // Slow enter
                            initialOffsetY = { it } // from bottom
                        )

                        val slideOut = slideOutVertically(
                            animationSpec = tween(durationMillis = 600),
                            targetOffsetY = { -it } // to top
                        )
                        slideIn togetherWith slideOut
                    },
                    label = "Hint Transition"
                ) { (isVisible, index) ->
                    var colour = Color.Transparent
                    if (isVisible) {
                        colour =  Color.Gray
                    }
                    Text(
                        text = hints[index],
                        color = colour,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedHintSearchTextField(
    hints: List<String>,
    prefix: String = "Search ",
    displayDuration: Long = 2000L,
    blankDuration: Long = 500L,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var currentHintIndex by remember { mutableIntStateOf(0) }
    var showHint by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("") }


    LaunchedEffect(currentHintIndex) {
        delay(displayDuration)
        showHint = false  // Exit hint
        delay(blankDuration)
        currentHintIndex = (currentHintIndex + 1) % hints.size
        showHint = true   // Show new hint
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        color = Color(0xFFF2F2F2),
        shadowElevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                // Only show animated placeholder when text is empty
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = prefix,
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                        AnimatedContent(
                            targetState = showHint to currentHintIndex,
                            transitionSpec = {
                                val slideIn = slideInVertically(
                                    animationSpec = tween(durationMillis = 600), // Slow enter
                                    initialOffsetY = { it } // from bottom
                                )

                                val slideOut = slideOutVertically(
                                    animationSpec = tween(durationMillis = 600),
                                    targetOffsetY = { -it } // to top
                                )
                                slideIn togetherWith slideOut
                            },
                            label = "Hint Transition"
                        ) { (isVisible, index) ->
                            var colour = Color.Transparent
                            if (isVisible) {
                                colour = Color.Gray
                            }
                            Text(
                                text = hints[index],
                                color = colour,
                                fontSize = 16.sp
                            )
                        }
                    }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(

                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            }
        )
    }
}