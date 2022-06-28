package com.template.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.template.base.EventHandler
import com.template.data.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fileRepository: FileRepository,
    application: Application,
): AndroidViewModel(application), EventHandler<MainEvent> {

    private val _mainViewState = MutableLiveData<MainViewState>(MainViewState.Loading)
    val mainViewState: LiveData<MainViewState> = _mainViewState

    private var count = 1

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _mainViewState.value) {
            is MainViewState.Loading -> reduce(event, currentState)
            is MainViewState.Display -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Loading) {
        when (event) {
            is MainEvent.LoadPage -> firstLoadPage()
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Display) {
        when (event) {
            is MainEvent.NextBtnClicked -> nextLoadPage()
        }
    }

    private fun firstLoadPage() {

        viewModelScope.launch(Dispatchers.IO) {

            val title = fileRepository.getTitle(
                fileRepository.readAssets(
                    getApplication<Application>().applicationContext,
                    "page$count.html"
                )
            )
            val content = fileRepository.getContent(
                fileRepository.readAssets(
                    getApplication<Application>().applicationContext,
                    "page$count.html"
                )
            )

            withContext(Dispatchers.Main) {
                _mainViewState.value =
                    MainViewState.Display(
                        title = title,
                        content = content,
                        isLastPage = false
                    )
            }
        }
    }

    private fun nextLoadPage() {

        viewModelScope.launch(Dispatchers.IO) {

            var title = ""
            var content = ""
            var isLastPage = false

            if (count < getApplication<Application>().applicationContext.assets.list("files")?.size!!) {
                count++
                title = fileRepository.getTitle(
                    fileRepository.readAssets(
                        getApplication<Application>().applicationContext,
                        "page$count.html"
                    )
                )
                content = fileRepository.getContent(
                    fileRepository.readAssets(
                        getApplication<Application>().applicationContext,
                        "page$count.html"
                    )
                )
            } else
                isLastPage = true

            Log.i("TAG", "nextLoadPage: $count")
            Log.i("TAG", "nextLoadPage: $isLastPage")


            withContext(Dispatchers.Main) {
                _mainViewState.value =
                    MainViewState.Display(
                        title = title,
                        content = content,
                        isLastPage = isLastPage
                    )
            }
        }
    }
}