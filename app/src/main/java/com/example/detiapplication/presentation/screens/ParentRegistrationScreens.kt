package com.example.detiapplication.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.example.detiapplication.domain.models.parent_models.*
import com.example.detiapplication.presentation.MainViewModel
import com.example.detiapplication.presentation.theme.*

@Composable
fun SearchChildrenScreen(navController: NavController, viewModel: MainViewModel, parentEmail: String) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val responseModel = remember {
        mutableStateOf(SearchChidldrenResponseModel("", "", ""))
    }
    val context = LocalContext.current

    Surface(color = Green) {
        Column {

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 15.dp, top = 20.dp)
                    .wrapContentHeight(CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    modifier = Modifier.size(45.dp),
                    tint = Color.Unspecified
                )
            }
            Card(
                modifier = Modifier.padding(top = 65.dp),
                shape = RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp)
            ) {
                Surface(color = Color.White) {
                    Column {
                        Text(
                            text = "Добавить ребенка",
                            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(1000)),
                            color = Black,
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(CenterHorizontally)
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(40.dp),
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Surface(color = GreenSomeLight) {
                                Column(verticalArrangement = Arrangement.Bottom) {

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(230.dp),
                                        shape = RoundedCornerShape(40.dp),
                                        border = BorderStroke(1.5.dp, LightBlack)
                                    ) {
                                        Surface(color = Color.White) {
                                            Column {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(top = 20.dp)
                                                ) {
                                                    Text(
                                                        text = responseModel.value.first_name.ifEmpty { "Ребенок" },
                                                        style = TextStyle(
                                                            fontSize = 22.sp,
                                                            fontWeight = FontWeight(800)
                                                        ),
                                                        modifier = Modifier.padding(start = 20.dp, end = 6.dp),
                                                        color = LightBlack
                                                    )
                                                    Text(
                                                        text = responseModel.value.last_name.ifEmpty { "не" },
                                                        style = TextStyle(
                                                            fontSize = 22.sp,
                                                            fontWeight = FontWeight(800)
                                                        ),
                                                        modifier = Modifier.padding(end = 20.dp),
                                                        color = LightBlack
                                                    )
                                                    Card(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(end = 15.dp),
                                                        shape = RoundedCornerShape(10.dp)
                                                    ) {
                                                        Surface(color = LightGreen) {
                                                            Text(
                                                                text = responseModel.value.age.ifEmpty { "найден" },
                                                                style = TextStyle(
                                                                    fontSize = 20.sp,
                                                                    fontWeight = FontWeight(800)
                                                                ),
                                                                modifier = Modifier.wrapContentWidth(CenterHorizontally),
                                                                color = Color.White
                                                            )
                                                        }
                                                    }
                                                }

                                                Button(
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 35.dp,
                                                            end = 35.dp,
                                                            top = 20.dp
                                                        )
                                                        .height(55.dp)
                                                        .fillMaxWidth(),
                                                    shape = RoundedCornerShape(15.dp),
                                                    colors = ButtonDefaults.buttonColors(LightOrange),
                                                    onClick = {
                                                        val parentToken = viewModel.getParentToken().token
                                                        viewModel.searchChildren(SearchChidlrenRequestModel(token = parentToken, parent_email = parentEmail))
                                                        viewModel.searchChildrenStatus.observe(lifecycleOwner) {
                                                            if(it == true) {
                                                                responseModel.value = SearchChidldrenResponseModel(
                                                                    first_name = viewModel.responseModel.value?.first_name.toString(),
                                                                    last_name = viewModel.responseModel.value?.last_name.toString(),
                                                                    age = viewModel.responseModel.value?.age.toString()
                                                                )
                                                            }
                                                        }
                                                    },
                                                ) {
                                                    Text(
                                                        text = "Найти ребенка",
                                                        style = TextStyle(
                                                            fontSize = 19.sp,
                                                            fontWeight = FontWeight(1000)
                                                        ),
                                                        color = Color.DarkGray,
                                                        modifier = Modifier
                                                            .wrapContentSize(Center)
                                                    )
                                                }

                                                Button(
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 35.dp,
                                                            end = 35.dp,
                                                            top = 20.dp
                                                        )
                                                        .height(55.dp)
                                                        .fillMaxWidth(),
                                                    shape = RoundedCornerShape(15.dp),
                                                    colors = ButtonDefaults.buttonColors(LightOrange),
                                                    onClick = {
                                                        val parentToken = viewModel.getParentToken().token
                                                        viewModel.addChildren(model = AddChildrenModel(token = parentToken))
                                                        viewModel.addChildrenStatus.observe(lifecycleOwner) {
                                                            if(it == true) {
                                                                viewModel.resetAddChildrenStatus()
                                                                Toast.makeText(context, "Children added", Toast.LENGTH_SHORT).show()
                                                            }
                                                            else if(it == false){
                                                                viewModel.resetAddChildrenStatus()
                                                                Toast.makeText(context, "Children adding error", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                        viewModel.resetChildrenModel()
                                                    },
                                                ) {
                                                    Text(
                                                        text = "Добавить",
                                                        style = TextStyle(
                                                            fontSize = 19.sp,
                                                            fontWeight = FontWeight(1000)
                                                        ),
                                                        color = Color.DarkGray,
                                                        modifier = Modifier
                                                            .wrapContentSize(Center)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                CircularProgressBar(
                                    isLoading = viewModel.loadingStatus.value,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(Center)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ParentSignInScreen(navController: NavController, viewModel: MainViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val wrongEmail = remember { mutableStateOf(false) }
    val wrongPassword = remember { mutableStateOf(false) }

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
                .padding(top = 83.dp)
                .fillMaxWidth()
                .wrapContentSize(Center)
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                wrongEmail.value = email.value.isEmpty()
            },
            label = {
                Text(
                    text = "Электронная почта",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
                )
            },
            modifier = Modifier
                .padding(top = 40.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongEmail.value) LightRed else LightBlack
            )
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                wrongPassword.value = password.value.isEmpty()
            },
            label = {
                Text(
                    text = "Пароль",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
                )
            },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongPassword.value) LightRed else LightBlack
            )
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 35.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                wrongEmail.value = email.value.isEmpty()
                wrongPassword.value = password.value.isEmpty()

                if(email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    viewModel.loginParent(
                        ParentLoginRequestModel(
                            email = email.value,
                            password = password.value
                        )
                    )

                    viewModel.parentLoginStatus.observe(lifecycleOwner) {
                        if(it == true) {
                            viewModel.resetLoginStatusParent()
                            Toast.makeText(context, "Successful login", Toast.LENGTH_SHORT).show()
                            navController.navigate("search_children_screen/${email.value}")
                        }
                        else if (it == false){
                            viewModel.resetLoginStatusParent()
                            Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show()
                        }
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
                    .wrapContentSize(Center)
            )
        }

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 20.dp)
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
                .padding(bottom = 15.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 15.dp, top = 20.dp)
                    .wrapContentHeight(CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "back",
                    modifier = Modifier.size(35.dp)
                )
            }

            CircularProgressBar(
                isLoading = viewModel.loadingStatus.value,
                modifier = Modifier.align(Center)
            )
        }
    }
}

@Composable
fun ParentInfoScreen(navController: NavController, viewModel: MainViewModel, email: String, password: String) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val wrongFirstName = remember { mutableStateOf(false) }
    val wrongSecondName = remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Личные данные",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 170.dp)
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally),
            color = Black
        )

        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            modifier = Modifier
                .padding(top = 50.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Имя",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongFirstName.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongFirstName.value) LightRed else LightBlack
            )
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            modifier = Modifier
                .padding(top = 10.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Фамилия",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongSecondName.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongSecondName.value) LightRed else LightBlack
            )
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 55.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                wrongFirstName.value = firstName.value.isEmpty()
                wrongSecondName.value = lastName.value.isEmpty()

                if(firstName.value.isNotEmpty() && lastName.value.isNotEmpty()) {
                    viewModel.registerParent(
                        ParentRegistrationRequestModel(
                            email = email,
                            password = password,
                            first_name = firstName.value,
                            last_name = lastName.value
                        )
                    )

                    viewModel.parentRegistrationStatus.observe(lifecycleOwner) {
                        if(it == true) {
                            viewModel.resetRegStatusParent()
                            Toast.makeText(context, "Successful registration", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screens.ParentQrScreen.route)
                        }
                        else if (it == false){
                            viewModel.resetRegStatusParent()
                            Toast.makeText(context, "Registration error", Toast.LENGTH_SHORT).show()
                        }
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
                .padding(bottom = 15.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 15.dp, top = 90.dp)
                    .wrapContentHeight(Alignment.Top)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "back",
                    modifier = Modifier.size(35.dp)
                )
            }

            CircularProgressBar(
                isLoading = viewModel.loadingStatus.value,
                modifier = Modifier.align(Center)
            )
        }
    }
}

@Composable
fun ParentQrScreen(navController: NavController) {
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

@Composable
fun ParentRegistrationScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val wrongEmail = remember { mutableStateOf(false) }
    val wrongPassword = remember { mutableStateOf(false) }

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

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .padding(top = 40.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Электронная почта",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongEmail.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongEmail.value) LightRed else LightBlack
            )
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Пароль",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongPassword.value) LightRed else LightBlack
            )
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
                wrongEmail.value = email.value.isEmpty()
                wrongPassword.value = password.value.isEmpty()
                if(email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    navController.navigate(route = "parent_info_screen/${email.value}/${password.value}")
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

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
                .padding(bottom = 15.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 15.dp, top = 40.dp)
                    .wrapContentHeight(CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "back",
                    modifier = Modifier.size(35.dp)
                )
            }
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