package by.itacademy.homework6

import android.content.Context

interface FileOperations {
    fun saveFile(context: Context, name: String)
    fun getFiles(context: Context)
    fun loadStorageState(context: Context): Boolean
}