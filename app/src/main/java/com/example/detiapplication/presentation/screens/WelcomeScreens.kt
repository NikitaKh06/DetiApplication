package com.example.detiapplication.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.detiapplication.R
import com.example.detiapplication.presentation.screens.Screens
import com.example.detiapplication.presentation.theme.Black

//@Preview (showSystemUi = true)
@Composable
fun SelectionScreen(navController: NavController) {
    Column {
        Icon(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "select_profile",
            modifier = Modifier
                .padding(top = 35.dp)
                .align(CenterHorizontally)
                .size(200.dp),
            tint = Color.Unspecified
        )

        Text(
            text = "Кто вы?",
            style = TextStyle(
                fontSize = 35.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 10.dp)
                .align(CenterHorizontally),
            color = Black
        )

        IconButton(
            onClick = {
                navController.navigate(route = Screens.ChildrenSignInScreen.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.select_children),
                contentDescription = "select_children",
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
                    ,
                tint = Color.Unspecified
            )
        }

        IconButton(
            onClick = {
                navController.navigate(route = Screens.ParentSignInScreen.route)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.select_parent),
                contentDescription = "select_parent",
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
                ,
                tint = Color.Unspecified
            )
        }

        IconButton(
            onClick = {  },
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp)
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "back",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}