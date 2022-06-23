package com.template.data

import android.content.Context
import java.io.BufferedReader

class FileRepository {

    val fileName = "page1.html"

    fun readAssets(context: Context, fileName: String): String =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)
}