package by.itacademy.contacts.presentation


import android.content.Context
import by.itacademy.contacts.data.Contact

interface DBOperations {
    fun changeContact(context: Context, contact: Contact, position: Int)
    fun getUsersFromDB(context: Context): List<Contact>
    fun removeContact(context: Context, position: Int)
    fun saveContactInDB(context: Context,contact: Contact)
}