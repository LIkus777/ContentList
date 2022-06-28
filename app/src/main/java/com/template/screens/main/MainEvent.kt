package com.template.screens.main

sealed class MainEvent {
    object LoadPage: MainEvent()
    object NextBtnClicked: MainEvent()
}
