package by.itacademy.homework5_2
import android.app.Application
import by.itacademy.homework5_2.data.DBHelper

class App : Application() {
    val dbHelper: DBHelper = DBHelper(this)
}