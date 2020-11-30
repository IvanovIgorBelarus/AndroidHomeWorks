package by.itacademy.homework5_2

import android.content.Context

interface DBOperations {
    fun changeContact(context: Context,contact: Contact, position: Int)
    fun getUsersFromDB(context: Context): List<Contact>
    fun removeContact(context: Context, position: Int)
    fun saveContactInDB(context: Context,contact: Contact)
}