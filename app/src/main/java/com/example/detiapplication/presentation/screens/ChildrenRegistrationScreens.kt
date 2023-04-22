package com.example.detiapplication.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
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
import com.example.detiapplication.domain.models.children_models.ChildrenLoginRequestModel
import com.example.detiapplication.domain.models.children_models.ChildrenRegistrationRequestModel
import com.example.detiapplication.presentation.MainViewModel
import com.example.detiapplication.presentation.theme.*

@Composable
fun CircularProgressBar(isLoading: Boolean, modifier: Modifier) {
    if(isLoading) {
        Box(modifier = modifier) {
            CircularProgressIndicator(
                color = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun ChildrenSignInScreen(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val parentEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
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
                .wrapContentSize(Alignment.Center)
        )

        Text(
            text = "Вход",
            style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight(1000)),
            color = Black,
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        OutlinedTextField(
            value = parentEmail.value,
            onValueChange = {
                parentEmail.value = it
                wrongEmail.value = parentEmail.value.isEmpty()
            },
            modifier = Modifier
                .padding(top = 50.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongEmail.value) LightRed else LightBlack
            ),
            label = {
                Text(
                    text = "Почта родителя",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
                )
            }
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                wrongPassword.value = password.value.isEmpty()
            },
            modifier = Modifier
                .padding(top = 3.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongPassword.value) LightRed else LightBlack
            ),
            label = {
                Text(
                    text = "Пароль",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
                )
            }
        )

        Button(
            modifier = Modifier
                .padding(start = 55.dp, end = 55.dp, top = 35.dp)
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                wrongEmail.value = parentEmail.value.isEmpty()
                wrongPassword.value = password.value.isEmpty()

                if(parentEmail.value.isNotEmpty() && password.value.isNotEmpty()) {
                    viewModel.loginChildren(
                        ChildrenLoginRequestModel(
                            parent_email = parentEmail.value,
                            password = password.value
                        )
                    )

                    viewModel.childrenLoginStatus.observe(lifecycleOwner) {
                        if(it == true) {
                            Toast.makeText(context, "Successful login", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screens.MainNavScreen.route)
                            viewModel.resetLoginStatus()
                        }
                        else if (it == false) {
                            Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show()
                            viewModel.resetLoginStatus()
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
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ChidlrenRegistrationScreen(navController: NavController) {
    val parentEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val wrongEmail = remember { mutableStateOf(false) }
    val wrongPassword = remember { mutableStateOf(false) }

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

        OutlinedTextField(
            value = parentEmail.value,
            onValueChange = {
                            parentEmail.value = it
            },
            modifier = Modifier
                .padding(top = 40.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Почта родителя",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongPassword.value) LightRed else LightBlack
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
                .padding(top = 43.dp, start = 55.dp, end = 55.dp)
                .height(55.dp)
                .fillMaxWidth()
                .align(CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Green),
            onClick = {
                wrongEmail.value = parentEmail.value.isEmpty()
                wrongPassword.value = password.value.isEmpty()
                if(parentEmail.value.isNotEmpty() && password.value.isNotEmpty()) {
                    navController.navigate("children_info_screen/${parentEmail.value}/${password.value}")
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

        Box(modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Bottom)
            .padding(bottom = 15.dp)) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(start = 15.dp, top = 30.dp)
                    .wrapContentHeight(Alignment.Top)
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

@Composable
fun ChildrenInfoScreen(
    navController: NavController,
    viewModel: MainViewModel,
    parentEmail: String,
    password: String
) {
    val context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val wrongFirstName = remember { mutableStateOf(false) }
    val wrongLastName = remember { mutableStateOf(false) }
    val wrongAge = remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Личные данные",
            style = TextStyle(
                fontSize = 33.sp,
                fontWeight = FontWeight(1000)
            ),
            modifier = Modifier
                .padding(top = 130.dp)
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
                .padding(top = 4.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Фамилия",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongLastName.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongLastName.value) LightRed else LightBlack
            )
        )

        OutlinedTextField(
            value = age.value,
            onValueChange = { age.value = it },
            modifier = Modifier
                .padding(top = 4.dp, start = 55.dp, end = 55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            label = {
                Text(
                    text = "Возраст",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(800)),
                    color = if(wrongAge.value) LightRed else LightBlack
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = if(wrongAge.value) LightRed else LightBlack
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
                wrongLastName.value = lastName.value.isEmpty()
                wrongAge.value = age.value.isEmpty()

                if(firstName.value.isNotEmpty() && lastName.value.isNotEmpty() && age.value.isNotEmpty()) {
                    viewModel.registerChildren(
                        ChildrenRegistrationRequestModel(
                            parent_email = parentEmail,
                            password = password,
                            first_name = firstName.value,
                            last_name = lastName.value,
                            age = age.value
                        )
                    )
                }

                viewModel.childrenRegistrationStatus.observe(lifecycleOwner) {
                    if (it == true) {
                        viewModel.resetRegStatus()
                        Toast.makeText(context, "Successful registration", Toast.LENGTH_SHORT).show()
                    }
                    else if (it == false){
                        Toast.makeText(context, "Registration error", Toast.LENGTH_SHORT).show()
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
                    .padding(start = 15.dp, top = 50.dp)
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
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ChildrenQrScreen(navController: NavController) {
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