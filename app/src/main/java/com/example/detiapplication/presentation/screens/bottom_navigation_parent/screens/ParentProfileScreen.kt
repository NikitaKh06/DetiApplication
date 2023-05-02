package com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.example.detiapplication.data.repositories.parent_repositories.ReadParentProfileRequestModel
import com.example.detiapplication.data.repositories.parent_repositories.ReadParentProfileResponseModel
import com.example.detiapplication.presentation.screens.CircularProgressBar
import com.example.detiapplication.presentation.screens.bottom_navigation_children.ExitAlertDialog
import com.example.detiapplication.presentation.theme.*
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParentProfileScreen(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = koinViewModel()
) {
    val exitDialogState  = remember { mutableStateOf(false) }
    val parentToken = viewModel.getParentToken().token
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val parentProfile = remember {
        mutableStateOf(
            ReadParentProfileResponseModel("Загрузка..", "", "")
        )
    }

    if(parentProfile.value.first_name == "Загрузка..") {
        viewModel.readParentProfile(
            ReadParentProfileRequestModel(
                token = parentToken
            )
        )
    }

    viewModel.parentProfile.observe(lifecycleOwner) {
        parentProfile.value = it
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
                    horizontalAlignment = CenterHorizontally
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
                                    text = parentProfile.value.first_name,
                                    modifier = Modifier.padding(end = 10.dp),
                                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(800)),
                                    color = Black
                                )
                                Text(
                                    text = parentProfile.value.last_name,
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
                .height(70.dp)
            ,
            colors = ButtonDefaults.buttonColors(LightOrange),
            onClick = {  },
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Редактировать",
                    modifier = Modifier.padding(end = 10.dp).fillMaxWidth().wrapContentWidth(CenterHorizontally),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
                Text(
                    text = "расписание",
                    modifier = Modifier.padding(end = 10.dp).fillMaxWidth().wrapContentWidth(CenterHorizontally),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                .height(65.dp)
            ,
            colors = ButtonDefaults.buttonColors(GreenSomeLight),
            onClick = {  },
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Добавить ребенка",
                modifier = Modifier.padding(end = 10.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800)),
                color = Color.White
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp)
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