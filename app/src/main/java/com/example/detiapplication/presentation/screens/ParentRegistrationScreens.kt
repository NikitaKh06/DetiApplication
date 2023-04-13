package com.example.detiapplication.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.detiapplication.R
import com.example.detiapplication.presentation.screens.Screens
import com.example.detiapplication.presentation.theme.Black
import com.example.detiapplication.presentation.theme.Green
import com.example.detiapplication.presentation.theme.LightBlack
import com.example.detiapplication.presentation.theme.LightGreen

//@Preview (showSystemUi = true)
@Composable
fun ParentSignInScreen(navController: NavController, viewModel: MainViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Column {
        Text(
            text = "deti",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000)),
            color = LightGreen,
            modifier = Modifier
                .padding(top = 45.dp)
                .fillMaxWidth()
                .wrapContentSize(Center)
        )

        Text(
            text = "Вход",
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight(1000)),
            color = Black,
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth()
                .wrapContentSize(Center)
        )

        Text(
            text = "Электронная почта",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 50.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Text(
            text = "Пароль",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 35.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {}
        ) {
            Text(
                text = "Продолжить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White,
                modifier = Modifier
                    .wrapContentSize(Center)
            )
        }

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 25.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(5.dp, Green),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {
                navController.navigate(route = Screens.ParentRegistrationScreen.route)
            },

            ) {
            Text(
                text = "Регистрация",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Green,
                modifier = Modifier
                    .wrapContentSize(Center)
            )
        }

        ClickableText(
            text = AnnotatedString("Забыли пароль?"),
            modifier = Modifier
                .padding(top = 7.dp)
                .align(CenterHorizontally),
            style = TextStyle(
                color = LightBlack,
                fontWeight = FontWeight(700),
                fontSize = 18.sp
            ),
            onClick = {}
        )

        IconButton(
            onClick = {
                navController.popBackStack()
            },
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

//@Preview (showSystemUi = true)
@Composable
fun ParentInfoScreen(navController: NavController, viewModel: MainViewModel) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Column {
        Text(
            text = "Личные данные",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally),
            color = Black
        )

        Text(
            text = "Имя",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 50.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Text(
            text = "Фамилия",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 55.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                navController.navigate(Screens.ParentQrScreen.route)
            }
        ) {
            Text(
                text = "Продолжить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White
            )
        }

        IconButton(
            onClick = {
                      navController.popBackStack()
            },
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

//@Preview (showSystemUi = true)
@Composable
fun ParentQrScreen(navController: NavController, viewModel: MainViewModel) {
    Column {
        Text(
            text = "Отсканируйте QR код ребенка",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(900)
            ),
            modifier = Modifier
                .padding(top = 50.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Black
        )

        Text(
            text = "Ребенок уже должен быть зарегистрированным",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700)
            ),
            modifier = Modifier
                .padding(top = 15.dp, start = 45.dp, end = 45.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = LightBlack
        )

        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 30.dp)
                .align(CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription = "qr",
                modifier = Modifier
                    .size(260.dp),
                tint = Color.Unspecified
            )
        }

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .height(55.dp)
                .width(260.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {}
        ) {
            Text(
                text = "Продолжить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White
            )
        }

        IconButton(
            onClick = {
                      navController.popBackStack()
            },
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

//@Preview (showSystemUi = true)
@Composable
fun ParentRegistrationScreen(navController: NavController, viewModel: MainViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Column {
        IconButton(
            modifier = Modifier
                .padding(top = 15.dp)
                .align(CenterHorizontally),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.avatar_edit),
                contentDescription = "edit_avatar",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(160.dp)
                    .align(CenterHorizontally),
                tint = Color.Unspecified
            )
        }

        Text(
            text = "Регистрация",
            style = TextStyle(
                fontSize = 34.sp,
                fontWeight = FontWeight(900)
            ),
            modifier = Modifier
                .padding(top = 45.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Black
        )

        Text(
            text = "Электронная почта",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 30.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Text(
            text = "Пароль",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 15.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp, start = 55.dp, end = 55.dp)
                .height(55.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                navController.navigate(Screens.ParentInfoScreen.route)
            }
        ) {
            Text(
                text = "Продолжить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White
            )
        }

        IconButton(
            onClick = {
                      navController.popBackStack()
            },
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


//@Preview (showSystemUi = true)
@Composable
fun ParentChangePasswordScreen() {
    Column {
        Text(
            text = "Смена пароля",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 170.dp)
                .fillMaxWidth()
                .wrapContentSize(Center),
            color = Green
        )

        Text(
            text = "Электронная почта",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 50.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Text(
            text = "Новый пароль",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 35.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {}
        ) {
            Text(
                text = "Сохранить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(top = 150.dp, start = 15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "back",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}