package com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsReceiveModel
import com.example.detiapplication.presentation.home_models.ReadListOfSubjectsRequestModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.screens.HomeScreens
import com.example.detiapplication.presentation.theme.*
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParentHomeScreen(navController: NavController, bottomPaddingValues: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val parentToken = viewModel.getParentToken().token
    var listOfSubjects = listOf<ReadListOfSubjectsReceiveModel>()

    viewModel.readListOfSubjectsFromParent(
        ReadListOfSubjectsRequestModel(
            token = parentToken,
            day = "Sunday"
        )
    )

    viewModel.listOfSubjects.observe(lifecycleOwner) {
        listOfSubjects = viewModel.listOfSubjects.value!!
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
                .height(70.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(LightOrange),
            onClick = {
                navController.navigate(HomeScreens.ChildrenProfileFromParent.route)
            }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Профиль ребенка",
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(start = 30.dp, end = 30.dp, top = 30.dp),
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
                            .align(Alignment.CenterHorizontally)
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
                            SubjectElementParent(model = model, navController = navController)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 29.dp, end = 29.dp),
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "15",
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight(800)
                            ),
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
                            color = Black
                        )
                        Text(
                            text = "Октября",
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
                            .wrapContentSize(Alignment.Center),
                        color = Black
                    )
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