package com.example.detiapplication.presentation.screens.bottom_navigation_children.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsReceiveModel
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsRequestModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.theme.*
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ChildrenHomeScreen(navController: NavController, bottomPaddingValues: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    val daysList = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val menuState = remember { mutableStateOf(false) }
    val requestState = remember { mutableStateOf(true) }
    val expanded = remember { mutableStateOf(false) }
    val day = remember { mutableStateOf(viewModel.weekDay.value!!) }
    val textDay = remember { mutableStateOf(when(day.value) {
        daysList[0] -> "Понедельник"
        daysList[1] -> "Вторник"
        daysList[2] -> "Среда"
        daysList[3] -> "Четверг"
        daysList[4] -> "Пятница"
        daysList[5] -> "Суббота"
        else -> "Воскресенье"
    }) }
    val calendar = Calendar.getInstance()
    val time = calendar.time
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH).format(time.time)
    val monthString = SimpleDateFormat("MMM", Locale.ENGLISH).format(time.time)
    val dayNumber = SimpleDateFormat("dd", Locale.ENGLISH).format(time.time)

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val childrenToken = viewModel.getChildrenToken().token
    var listOfSubjects = listOf<ReadListOfSubjectsReceiveModel>()
    val context = LocalContext.current


    if(requestState.value) {
        viewModel.readListOfSubjectsFromChildren(
            ReadListOfSubjectsRequestModel(
                token = childrenToken,
                day = day.value
            )
        )
        requestState.value = false
    }

    viewModel.listOfSubjects.observe(lifecycleOwner) {
        listOfSubjects = viewModel.listOfSubjects.value!!
        menuState.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPaddingValues.calculateBottomPadding() + 15.dp)
    ) {
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
                        text = textDay.value,
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
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
                            .wrapContentSize(Alignment.Center),
                        color = Color.White
                    )
                    LazyColumn {
                        items(listOfSubjects) { model ->
                            SubjectElement(model = model, navController = navController)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, start = 29.dp, end = 29.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(120.dp)
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dayNumber,
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight(800)
                            ),
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                            color = Black
                        )
                        Text(
                            text = when(monthString) {
                                "January" -> "Января"
                                "February" -> "Февраля"
                                "March" -> "Марта"
                                "April" -> "Апреля"
                                "May" -> "Мая"
                                "June" -> "Июня"
                                "July" -> "Июля"
                                "August" -> "Августа"
                                "September" -> "Сентября"
                                "October" -> "Октябрь"
                                "November" -> "Ноября"
                                else-> "Декабря" },
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight(800)
                            ),
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                            color = Black
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Surface(color = LightOrange) {
                    Text(
                        text = when(dayOfWeek) {
                            daysList[0] -> "Понедельник"
                            daysList[1] -> "Вторник"
                            daysList[2] -> "Среда"
                            daysList[3] -> "Четверг"
                            daysList[4] -> "Пятница"
                            daysList[5] -> "Суббота"
                            else -> "Воскресенье"
                        },
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight(800)
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        color = Black
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 85.dp, end = 85.dp, top = 25.dp)
                .height(70.dp),
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(Red),
            onClick = {
                val number = Uri.parse("tel:" + "112")
                val i = Intent(Intent.ACTION_DIAL, number)
                try {
                    context.startActivity(i)
                } catch (_: SecurityException) { }
            }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SOS",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }
        Log.d("MyLog", day.value)
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
                            viewModel.readListOfSubjectsFromChildren(
                                ReadListOfSubjectsRequestModel(
                                    token = childrenToken,
                                    day = day.value
                                )
                            )
                            viewModel.changeDay(day = day.value)
                            textDay.value = when(day.value) {
                                daysList[0] -> "Понедельник"
                                daysList[1] -> "Вторник"
                                daysList[2] -> "Среда"
                                daysList[3] -> "Четверг"
                                daysList[4] -> "Пятница"
                                daysList[5] -> "Суббота"
                                else -> "Воскресенье"
                            }
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
fun SubjectElement(model: ReadListOfSubjectsReceiveModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .clickable {
                navController.navigate(route = "children_homework_screen/${model.id}/${model.title}")
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