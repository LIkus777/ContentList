package com.template.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.base.EventHandler
import com.template.data.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val fileRepository: FileRepository
): ViewModel(), EventHandler<MainEvent>{

    private val _mainViewState = MutableLiveData<MainViewState>(MainViewState.Loading)
    val mainViewState: LiveData<MainViewState> = _mainViewState

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _mainViewState.value) {
            is MainViewState.Loading -> reduce(event, currentState)
            is MainViewState.Display -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Loading) {
        TODO("Not yet implemented")
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Display) {
        TODO("Not yet implemented")
    }
}