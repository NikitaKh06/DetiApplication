package com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.detiapplication.presentation.home_models.AddCommentRequestModel
import com.example.detiapplication.presentation.home_models.ReadFullSubjectRequestModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.theme.Black
import com.example.detiapplication.presentation.theme.GreenSomeLight
import com.example.detiapplication.presentation.theme.LightBlack
import com.example.detiapplication.presentation.theme.LightOrange
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParenHomeworkScreen(
    id: String, title: String, viewModel: HomeViewModel = koinViewModel(), paddingValues: PaddingValues
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val comment = remember {
        mutableStateOf("")
    }
    val homework = remember {
        mutableStateOf("")
    }

    val addCommentDialogState  = remember { mutableStateOf(false) }

    if(addCommentDialogState.value) {
        AddCommentScreen(addDialogState = addCommentDialogState, viewModel = viewModel, id = id, title = title)
    }

    if(comment.value == "") {
        viewModel.readFullSubject(
            ReadFullSubjectRequestModel(
                subject_id = id
            )
        )
    }

    viewModel.fullSubject.observe(lifecycleOwner) {
        comment.value = viewModel.fullSubject.value!!.comment
        homework.value = viewModel.fullSubject.value!!.homework
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight(1000)),
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 45.dp)
        )

        Text(
            text = "Комментарий",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier.padding(start = 30.dp, top = 70.dp)
        )

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 5.dp)
            .height(60.dp),
            border = BorderStroke(2.dp, Black),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = comment.value,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                color = LightBlack,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(start = 15.dp)
            )
        }

        Text(
            text = "Домашнее задание",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier.padding(start = 30.dp, top = 20.dp)
        )

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 5.dp)
            .height(60.dp),
            border = BorderStroke(2.dp, Black),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = homework.value,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                color = LightBlack,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(start = 15.dp)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 50.dp)
                .height(65.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {

            }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Напомнить",
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 15.dp)
                .height(70.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {
                addCommentDialogState.value = true
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Добавить",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
                Text(
                    text = "комментарий",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
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

@Composable
fun AddCommentScreen(addDialogState: MutableState<Boolean>, viewModel: HomeViewModel, id: String, title: String) {
    val text = remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = { addDialogState.value = false },
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight(800)),
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.addComment(
                        AddCommentRequestModel(
                            subject_id = id,
                            text = text.value
                        )
                    )
                    addDialogState.value = false
                },
                colors = ButtonDefaults.buttonColors(LightOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Добавить",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
        }
    )
}