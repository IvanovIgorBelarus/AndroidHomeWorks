package by.itacademy.contacts
import android.app.Application
import by.itacademy.contacts.data.DBHelper

class App : Application() {
    val dbHelper: DBHelper = DBHelper(this)
}