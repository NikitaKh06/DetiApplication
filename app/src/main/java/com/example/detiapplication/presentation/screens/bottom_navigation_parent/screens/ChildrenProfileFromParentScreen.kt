package com.example.detiapplication.presentation.screens.bottom_navigation_parent.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.detiapplication.data.repositories.parent_repositories.ReadChildrenProfileFromParentRequestModel
import com.example.detiapplication.data.repositories.parent_repositories.ReadChildrenProfileFromParentResponseModel
import com.example.detiapplication.presentation.theme.Black
import com.example.detiapplication.presentation.theme.GreenSomeLight
import com.example.detiapplication.presentation.theme.LightOrange
import com.example.detiapplication.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Preview(showSystemUi = true)
@Composable
fun ChildrenProfileFromParentScreen(viewModel: HomeViewModel = koinViewModel()) {
    val parentToken = viewModel.getParentToken().token
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val childrenProfile = remember {
        mutableStateOf(
            ReadChildrenProfileFromParentResponseModel("Загрузка..", "", "...", photo = "")
        )
    }

    if(childrenProfile.value.first_name == "Загрузка..") {
        viewModel.readChildrenProfileFromParent(
            ReadChildrenProfileFromParentRequestModel(
                token = parentToken
            )
        )
    }

    viewModel.childrenProfileFromParent.observe(lifecycleOwner) {
        childrenProfile.value = it
    }

    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            shape = RoundedCornerShape(bottomEnd = 35.dp, bottomStart = 35.dp)
        ) {
            Surface(color = GreenSomeLight) {

            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp, top = 50.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column() {
                Text(
                    text = childrenProfile.value.first_name,
                    style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
                Text(
                    text = childrenProfile.value.last_name,
                    style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight(800)),
                    color = Black
                )
            }
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Surface(color = LightOrange) {
                    Text(
                        text = childrenProfile.value.age,
                        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight(800)),
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