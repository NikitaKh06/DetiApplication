package com.example.detiapplication.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(isLoading: Boolean, modifier: Modifier, color: Color = Color.Gray) {
    if(isLoading) {
        Box(modifier = modifier) {
            CircularProgressIndicator(
                color = color,
                modifier = Modifier.size(25.dp),
            )
        }
    }
}