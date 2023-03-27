package com.jephtecolin.varoflix.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PulsatingHeartIcon() {
    val infiniteTransition = rememberInfiniteTransition()
    val pulsate by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 60f,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
    )
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "",
        modifier = Modifier
            .size(pulsate.dp)
            .offset(
                x = 10.dp, y = 10.dp
            )
    )
}