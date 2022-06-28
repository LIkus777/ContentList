package com.template.screens.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.template.ui.theme.AccentColor
import com.template.ui.theme.PrimaryColor

@Composable
fun MainScreen(mainViewModel: MainViewModel, navController: NavController) {

    val viewState = mainViewModel.mainViewState.observeAsState()



    Surface() {

        when (val state = viewState.value) {
            is MainViewState.Display -> DisplayView(
                navController = navController,
                state = state
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    mainViewModel.obtainEvent(event = MainEvent.NextBtnClicked)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = AccentColor),
            ) {
                Text(text = "NEXT", color = Color.White)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        mainViewModel.obtainEvent(event = MainEvent.LoadPage)
/*
        onDispose {
            mainViewModel.mainViewState.removeObserver()
        }
*/
    }
}