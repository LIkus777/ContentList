package com.template.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.template.screens.navigation.NavigationTree
import com.template.ui.theme.PrimaryColor

@Composable
fun DisplayView(navController: NavController, state: MainViewState.Display) {

    Column(
        Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
    ) {

        val scrollState = rememberScrollState(0)
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp).background(color = Color.White),
            progress = scrollState.value.toFloat() / scrollState.maxValue
        )

        Text(
            text = "${state.title} \n ${state.content}",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
        )
    }

    DisposableEffect(key1 = state.isLastPage) {
        onDispose {
            navController.navigate(NavigationTree.Finish.name)
        }
    }
}