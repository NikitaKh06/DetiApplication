package com.example.detiapplication.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.detiapplication.presentation.theme.*

@Composable
fun SubjectElement() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(color = GreenMoreDark) {
            Column() {
                Text(
                    text = "Математика",
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(800)),
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = "14:30 - 15:30",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
                    color = CustomWhite,
                    modifier = Modifier.padding(start = 22.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChildrenScheduleMainScreen() {
    Column {
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(start = 30.dp, end = 30.dp, top = 5.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Surface(color = GreenSomeLight) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Вторник",
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                        color = Color.White,
                        modifier = Modifier
                            .align(CenterHorizontally)
                    )

                    SubjectElement()
                }
            }
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 17.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(130.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Surface(
                    color = LightOrange
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "15",
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight(800)
                            ),
                            modifier = Modifier.wrapContentWidth(CenterHorizontally),
                            color = Black
                        )
                        Text(
                            text = "Октября",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight(800)
                            ),
                            modifier = Modifier.wrapContentWidth(CenterHorizontally),
                            color = Black
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .width(180.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Surface(color = LightOrange) {
                    Text(
                        text = "Понедельник",
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight(800)
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Center),
                        color = Black
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 17.dp)
                .height(80.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {
                /*TODO*/
            }
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    text = "Добавить",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
                Text(
                    text = "домашнее задание",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 85.dp, end = 85.dp, top = 17.dp)
                .height(70.dp),
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(Red),
            onClick = {
                /*TODO*/
            }
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    text = "SOS",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }
    }
}