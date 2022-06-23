package com.template.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val viewState = mainViewModel.mainViewState.observeAsState()

    Surface() {
        LazyColumn() {
            item {
                when(val state = viewState.value) {
                    is MainViewState.Loading -> DisplayView() //todo создать параметры для этих фукнций
                    is MainViewState.Display -> DisplayView()
                }
            }
        }
    }
    
    LaunchedEffect(key1 = Unit) {
        mainViewModel.obtainEvent(event = MainEvent.LoadPage)
    }
}