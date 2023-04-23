package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.detiapplication.presentation.theme.LightBlack

@Preview(showSystemUi = true)
@Composable
fun ChildrenSettingsScreen() {
    Text(
        text = "Settings",
        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000)),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(CenterHorizontally),
        color = LightBlack
    )
}