package com.example.detiapplication.presentation.screens.bottom_navigation_children

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.detiapplication.R
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileResponseModel
import com.example.detiapplication.data.models.children_models.ReadChildrenProfileRequestModel
import com.example.detiapplication.data.repositories.children_repositories.ChildrenRegistrationRepository
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.theme.*
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import com.jakewharton.processphoenix.ProcessPhoenix
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@Composable
fun ChildrenProfileScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = koinViewModel()
) {
    val exitDialogState  = remember { mutableStateOf(false) }
    val childrenToken = viewModel.getChildrenToken().token
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var childrenProfile = remember {
        mutableStateOf(
            ReadChildrenProfileResponseModel("Загрузка..", "", "", "", "")
        )
    }

    if(childrenProfile.value.first_name == "Загрузка..") {
        viewModel.readChildrenProfile(
            ReadChildrenProfileRequestModel(
                token = childrenToken
            )
        )
    }

    viewModel.childrenProfile.observe(lifecycleOwner) {
        childrenProfile.value = it
    }

    if(exitDialogState.value) {
        ExitAlertDialog(exitDialogState = exitDialogState)
    }
    
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
                                    text = childrenProfile.value.first_name,
                                    modifier = Modifier.padding(end = 10.dp),
                                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(800)),
                                    color = Black
                                )
                                Text(
                                    text = childrenProfile.value.last_name,
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
            onClick = {  },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Редактировать профиль",
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
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
            onClick = {
                exitDialogState.value = true
            },
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Выйти",
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                color = GreenMoreDark
            )
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
fun ExitAlertDialog(exitDialogState: MutableState<Boolean>,
                     childrenRegistrationRepository: ChildrenRegistrationRepository = koinInject(),
                     context: Context = koinInject()
) {
    AlertDialog(
        onDismissRequest = { exitDialogState.value = false },
        shape = RoundedCornerShape(20.dp),
        title = {
            Column {
                Text(
                    text = "Вы точно хотите выйти?",
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight(800)),
                    color = Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    childrenRegistrationRepository.clearLogInStatus()
                    ProcessPhoenix.triggerRebirth(context)
                    exitDialogState.value = false
                },
                colors = ButtonDefaults.buttonColors(Green),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Выйти",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Color.White
                )
            }
        }
    )
}