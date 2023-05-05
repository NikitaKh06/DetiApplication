package com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.detiapplication.presentation.home_models.AddSubjectRequestModel
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsReceiveModel
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsRequestModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.screens.HomeScreens
import com.example.detiapplication.presentation.theme.*
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParentHomeScreen(navController: NavController, bottomPaddingValues: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    val daysList = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val parentToken = viewModel.getParentToken().token
    var listOfSubjects = remember {
        mutableListOf<ReadListOfSubjectsReceiveModel>()
    }
    val addSubjectDialogState  = remember { mutableStateOf(false) }
    val day = remember { mutableStateOf(daysList[0]) }
    val expanded = remember { mutableStateOf(false) }
    val requestState = remember { mutableStateOf(true) }
    val menuState = remember { mutableStateOf(false) }

    if(addSubjectDialogState.value) {
        AddSubjectScreen(addDialogState = addSubjectDialogState, viewModel = viewModel, daysList = daysList)
    }

    if(requestState.value) {
        viewModel.readListOfSubjectsFromParent(
            ReadListOfSubjectsRequestModel(
                token = parentToken,
                day = day.value
            )
        )
        requestState.value = false
    }

    viewModel.listOfSubjects.observe(lifecycleOwner) {
        listOfSubjects = it
        menuState.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPaddingValues.calculateBottomPadding() + 15.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LightOrange),
            onClick = {
                navController.navigate(HomeScreens.ChildrenProfileFromParent.route)
            }
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    text = "Профиль ребенка",
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
        }
        if(viewModel.loadingStatus.value) {
            menuState.value = false
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(start = 30.dp, end = 30.dp, top = 15.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Surface(color = GreenSomeLight) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = day.value,
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                        color = Color.White,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .clickable {
                                if (menuState.value) {
                                    expanded.value = true
                                }
                            }
                    )
                    CircularProgressBar(
                        isLoading = viewModel.loadingStatus.value,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Center),
                        color = Color.White
                    )
                    LazyColumn {
                        items(listOfSubjects) { model ->
                            SubjectElementParent(model = model, navController = navController)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 29.dp, end = 29.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(140.dp)
                    .height(80.dp)
                    .padding(end = 10.dp),
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
                    .width(190.dp)
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
                .padding(start = 30.dp, end = 30.dp, top = 15.dp)
                .height(70.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {
                addSubjectDialogState.value = true
            }
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    text = "Добавить предмет",
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                offset = DpOffset(x = 130.dp, y = 150.dp)
            ) {
                daysList.forEach {
                    DropdownMenuItem(
                        onClick = {
                            requestState.value = true
                            day.value = it
                            expanded.value = false
                            viewModel.cleanList()
                            viewModel.readListOfSubjectsFromParent(
                                ReadListOfSubjectsRequestModel(
                                    token = parentToken,
                                    day = day.value
                                )
                            )
                        }
                    ) {
                        Text(
                            text = when(it) {
                                daysList[0] -> "Понедельник"
                                daysList[1] -> "Вторник"
                                daysList[2] -> "Среда"
                                daysList[3] -> "Четверг"
                                daysList[4] -> "Пятница"
                                daysList[5] -> "Суббота"
                                else -> "Воскресенье"
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SubjectElementParent(model: ReadListOfSubjectsReceiveModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .clickable {
                navController.navigate(route = "parent_homework_screen/${model.id}/${model.title}")
            },
        shape = RoundedCornerShape(10.dp),

        ) {
        Surface(color = GreenMoreDark) {
            Column {
                Text(
                    text = model.title,
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(800)),
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text(
                    text = "${model.hours}:${model.minutes} - 00:00",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(800)),
                    color = CustomWhite,
                    modifier = Modifier.padding(start = 22.dp)
                )
            }
        }
    }
}

@Composable
fun AddSubjectScreen(
    addDialogState: MutableState<Boolean>, viewModel: HomeViewModel, daysList: List<String>
) {
    val context = LocalContext.current
    val parentToken = viewModel.getParentToken().token
    val text = remember {
        mutableStateOf("")
    }
    var expanded by remember { mutableStateOf(false) }
    val day = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { addDialogState.value = false },
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Text(
                    text = "Добавление занятия",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                )
                Text(
                    text = "Название",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = LightBlack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(top = 15.dp)
                )

                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                        .padding(top = 3.dp),
                    shape = RoundedCornerShape(20.dp)
                )

                Button(
                    onClick = {
                        expanded = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(top = 15.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(LightOrange)
                ) {
                    Text(
                        text = "День недели",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                        color = Black
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    daysList.forEach {
                        DropdownMenuItem(
                            onClick = {
                                day.value = it
                                expanded = false
                            }
                        ) {
                            Text(
                                text = when(it) {
                                    daysList[0] -> "Понедельник"
                                    daysList[1] -> "Вторник"
                                    daysList[2] -> "Среда"
                                    daysList[3] -> "Четверг"
                                    daysList[4] -> "Пятница"
                                    daysList[5] -> "Суббота"
                                    else -> "Воскресенье"
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if(day.value.isNotEmpty() && text.value.isNotEmpty()) {
                        viewModel.addSubject(
                            AddSubjectRequestModel(
                                token = parentToken,
                                title = text.value,
                                day = day.value,
                                hours = "00",
                                minutes = "00"
                            )
                        )
                        addDialogState.value = false
                        day.value = ""
                    }
                    else {
                        Toast.makeText(context, "Заполните данные занятия", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(LightOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
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