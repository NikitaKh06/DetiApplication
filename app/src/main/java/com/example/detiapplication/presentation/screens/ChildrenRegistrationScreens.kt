package com.example.detiapplication.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.detiapplication.R
import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.presentation.screens.Screens
import com.example.detiapplication.presentation.theme.Black
import com.example.detiapplication.presentation.theme.Green
import com.example.detiapplication.presentation.theme.LightBlack
import com.example.detiapplication.presentation.theme.LightGreen

//@Preview(showSystemUi = true)
@Composable
fun ChildrenSignInScreen(navController: NavController, viewModel: MainViewModel) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val parentEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column {
        Text(
            text = "deti",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000)),
            color = LightGreen,
            modifier = Modifier
                .padding(top = 45.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Text(
            text = "Вход",
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight(1000)),
            color = Black,
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Text(
            text = "Почта родителя",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 50.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = parentEmail.value,
            onValueChange = { parentEmail.value = it },
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
            onClick = {

                viewModel.loginChildren(
                    ChildrenLoginRequestModel(
                        parent_email = parentEmail.value,
                        password = password.value
                    )
                )

                viewModel.loginStatus.observe(lifecycleOwner) {
                    if(it == true) {
                        Log.d("MyLog", "Login succesful")
                        viewModel.resetLoginStatus()
                    }
                    else if (it == false) {
                        Log.d("MyLog", "Login error")
                        viewModel.resetRegStatus()
                    }
                }
            }
        ) {
            Text(
                text = "Продолжить",
                style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700)
                ),
                color = Color.White,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
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
                viewModel.resetLoginStatus()
                navController.navigate(Screens.ChildrenRegistrationScreen.route)
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
                    .wrapContentSize(Alignment.Center)
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
                .wrapContentHeight(Alignment.Bottom)
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
fun ChidlrenRegistrationScreen(navController: NavController, viewModel: MainViewModel) {
    val parentEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Column {
        IconButton(
            modifier = Modifier
                .padding(top = 15.dp)
                .align(CenterHorizontally),
            onClick = {
                navController.navigate(Screens.ChildrenInfoScreen.route)
            }
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
            text = "Почта родителя",
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
            color = LightBlack,
            modifier = Modifier
                .padding(start = 60.dp, top = 30.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = parentEmail.value,
            onValueChange = {
                            parentEmail.value = it
            },
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
                viewModel.registerChildren(
                    ChildrenRegistrationRequestModel(
                        parent_email = parentEmail.value,
                        password = password.value,
                        first_name = "Nikita",
                        last_name = "LastNikita",
                        age = "17"
                    )
                )
                viewModel.registrationStatus.observe(lifecycleOwner) {
                    if (it == true) {
                        viewModel.resetRegStatus()
                        Log.d("MyLog", "Succesful registration")
                        navController.navigate(Screens.ChildrenInfoScreen.route)
                    }
                    else if (it == false){
                        Log.d("MyLog", "Registration error")
                        viewModel.resetRegStatus()
                    }
                }

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
                .wrapContentHeight(Alignment.Bottom)
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
fun ChildrenInfoScreen(navController: NavController, viewModel: MainViewModel) {
    Column {
        Text(
            text = "Личные данные",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 100.dp)
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        )

        Text(
            text = "Возраст",
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
                .padding(start = 55.dp, end = 55.dp, top = 55.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                navController.navigate(Screens.ChildrenQrScreen.route)
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
                .wrapContentHeight(Alignment.Bottom)
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
fun ChildrenQrScreen(navController: NavController, viewModel: MainViewModel) {
    Column {
        Text(
            text = "Твой QR-код",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(900)
            ),
            modifier = Modifier
                .padding(top = 60.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Black
        )

        Text(
            text = "Родитель должен отсканировать его у себя в приложении",
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

        Icon(
            painter = painterResource(id = R.drawable.qr_code_pic),
            contentDescription = "qr",
            modifier = Modifier
                .size(260.dp)
                .padding(top = 30.dp)
                .align(CenterHorizontally),
            tint = Color.Unspecified
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .height(55.dp)
                .width(230.dp)
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
                .wrapContentHeight(Alignment.Bottom)
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
fun ChilrenChangePasswordScreen() {
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
                .wrapContentSize(Alignment.Center),
            color = Green
        )

        Text(
            text = "Почта родителя",
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