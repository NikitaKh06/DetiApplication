package com.example.detiapplication.presentation.screens.bottom_navigation_parent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.detiapplication.presentation.theme.LightBlack

@Composable
fun ParentSettingsScreen() {
    Text(
        text = "Settings",
        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000)),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        color = LightBlack
    )
}