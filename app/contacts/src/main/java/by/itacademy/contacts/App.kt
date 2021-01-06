package by.itacademy.contacts
import android.app.Application

class App : Application() {
    val dbHelper: DBHelper = DBHelper(this)
}