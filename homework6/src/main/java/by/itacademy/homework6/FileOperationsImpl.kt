package by.itacademy.homework6

import android.content.Context
import by.itacademy.homework6.Data.Companion.dataInstance
import java.io.File

class FileOperationsImpl(private val context: Context) : FileOperations {

    override fun saveFile(name: String) {
        if (loadStorageState()) {
            File(context.filesDir, name).createNewFile()
        } else {
            File(context.getExternalFilesDir(context.packageName), name).createNewFile()
        }
        dataInstance.fileList.add(name)
    }

    override fun getFiles() {
        if (loadStorageState()) {
            context.filesDir.listFiles { file -> dataInstance.fileList.add(file.name) }
        } else {
            context.getExternalFilesDir(context.packageName)?.listFiles { file -> dataInstance.fileList.add(file.name) }
        }
    }
    override fun saveStorageState(storageState: Boolean) {
        val pref = context.getSharedPreferences("settingStorage", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("1", storageState)
        editor.apply()
    }

    override fun loadStorageState() = context.getSharedPreferences("settingStorage", Context.MODE_PRIVATE).getBoolean("1", true)

}