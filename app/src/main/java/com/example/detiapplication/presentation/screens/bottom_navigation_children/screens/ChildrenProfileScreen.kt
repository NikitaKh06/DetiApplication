package com.example.detiapplication.presentation.screens.bottom_navigation_children

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.detiapplication.R
import com.example.detiapplication.presentation.theme.*


@Composable
fun ChildrenProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding() + 15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Surface(color = GreenSomeLight) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.avatar_edit),
                        contentDescription = "edit_avatar",
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .size(190.dp),
                        tint = Color.Unspecified
                    )
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                        .height(70.dp),
                        elevation = 5.dp,
                        shape = RoundedCornerShape(35.dp)
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                        ) {
                            Row(modifier = Modifier
                                .align(Alignment.Center)
                            ) {
                                Text(
                                    text = "Имя",
                                    modifier = Modifier.padding(end = 10.dp),
                                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(800)),
                                    color = Black
                                )
                                Text(
                                    text = "Фамилия",
                                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(800)),
                                    color = Black
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 30.dp, end = 30.dp)
                .height(65.dp)
            ,
            colors = ButtonDefaults.buttonColors(LightOrange),
            onClick = {
                      /*TODO*/
                      },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Редактировать профиль",
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(800)),
                color = Black
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                .height(65.dp)
            ,
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(3.dp, GreenMoreDark),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Выйти",
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                color = GreenMoreDark
            )
        }

    }
}