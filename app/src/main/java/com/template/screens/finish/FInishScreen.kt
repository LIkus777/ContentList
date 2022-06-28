package com.template.screens.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.template.screens.navigation.NavigationTree
import com.template.ui.theme.AccentColor
import com.template.ui.theme.PrimaryColor

@Composable
fun FinishScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = AccentColor),
            onClick = {
                navController.navigate(NavigationTree.Splash.name)
            }) {
            Text(text = "Start over")
        }
    }
}