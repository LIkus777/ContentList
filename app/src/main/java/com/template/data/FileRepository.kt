package com.template.data

import android.content.Context
import android.util.Log
import org.jsoup.Jsoup
import java.io.BufferedReader
import javax.inject.Inject


class FileRepository @Inject constructor() {

    fun readAssets(context: Context, fileName: String): String {
        Log.i("TAG", "readAssets: ${Thread.currentThread().name}")
        val file = context
            .assets
            .open("files/$fileName")
            .bufferedReader().readText()
        return file
    }

    fun getTitle(htmlString: String): String {
        val document = Jsoup.parse(htmlString)
        Log.i("TAG", "getTitle: ${Thread.currentThread().name}")
        return document.title()
    }

    fun getContent(htmlString: String): String {
        val document = Jsoup.parse(htmlString)
        Log.i("TAG", "getContent: ${Thread.currentThread().name}")
        return document.getElementsByTag("p").text()
    }

}