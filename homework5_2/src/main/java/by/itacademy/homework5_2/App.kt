package by.itacademy.homework5_2
import android.app.Application

class App : Application() {
    val dbHelper: DBHelper = DBHelper(this)
}