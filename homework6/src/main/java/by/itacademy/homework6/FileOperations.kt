package by.itacademy.homework6

import android.content.Context

interface FileOperations {
    fun saveFile(name: String)
    fun getFiles()
    fun saveStorageState( storageState: Boolean)
    fun loadStorageState(): Boolean
}