package by.itacademy.homework6

import android.content.Context
import by.itacademy.homework6.Data.Companion.dataInstance
import by.itacademy.homework6.SettingsActivity.Companion.isInternalStorage
import java.io.File

class FileOperationsImpl() : FileOperations {
    override fun saveFile(context: Context, name: String) {
        if (isInternalStorage) {
            File(context.filesDir, name).createNewFile()
        } else {
            File(context.getExternalFilesDir(context.packageName), name).createNewFile()
        }
        dataInstance.fileList.add(name)
    }

    override fun getFiles(context: Context) {
        if (isInternalStorage) {
            context.filesDir.listFiles { file -> dataInstance.fileList.add(file.name) }
        } else {
            context.getExternalFilesDir(context.packageName)?.listFiles { file -> dataInstance.fileList.add(file.name) }
        }
    }

    override fun loadStorageState(context: Context)
    = context.getSharedPreferences("settingStorage", Context.MODE_PRIVATE).getBoolean("1", true)

}