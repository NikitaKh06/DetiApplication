package com.example.detiapplication.presentation.screens.bottom_navigation_children.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.detiapplication.presentation.home_models.ReadFullSubjectRequestModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.theme.Black
import com.example.detiapplication.presentation.theme.GreenSomeLight
import com.example.detiapplication.presentation.theme.LightBlack
import com.example.detiapplication.presentation.theme.LightOrange
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChildrenHomeworkScreen(id: String, title: String, viewModel: HomeViewModel = koinViewModel()) {
    val subject = remember {
        mutableStateOf("")
    }
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    if(subject.value == "") {
        viewModel.readFullSubject(
            ReadFullSubjectRequestModel(
                subject_id = id
            )
        )
    }
    viewModel.fullSubject.observe(lifecycleOwner) {
        subject.value = viewModel.fullSubject.value!!.comment
    }

    Column {
        Text(
            text = title,
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight(1000)),
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
        )
        Text(
            text = "Комментарий от родителя",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp)
        )

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .height(70.dp),
            border = BorderStroke(2.dp, Black),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = subject.value,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                color = LightBlack,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(start = 15.dp)
            )
        }

        Text(
            text = "Маршрут",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier.padding(start = 30.dp, top = 20.dp)
        )
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
            .height(200.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Surface(color = GreenSomeLight) {

            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(LightOrange),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Я пришел",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(LightOrange),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Я иду",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                .height(80.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {

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
        CircularProgressBar(
            isLoading = viewModel.loadingStatus.value,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            color = Color.Gray
        )
    }
}