package com.template.screens.main

sealed class MainViewState {
    object Loading : MainViewState()
    data class Display(
        val title: String,
        val content: String,
        val isLastPage: Boolean
    ) : MainViewState()
}
