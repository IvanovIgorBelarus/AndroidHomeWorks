package by.itacademy.homework5_2.data

import android.content.ContentValues
import android.content.Context
import by.itacademy.homework5_2.App

class DBOperationsImpl(private val context: Context) : DBOperations {
    override fun saveContactInDB(contact: Contact) {
        val contentValues = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        (context.applicationContext as App)
                .dbHelper
                .writableDatabase
                .insert("contacts", null, contentValues)
    }

    override fun changeContact(contact: Contact, position: Int) {
        val contentValue = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        (context.applicationContext as App)
                .dbHelper
                .writableDatabase
                .update("contacts", contentValue, "" + getUsersFromDB()[position].id + " =id", null)
    }

    override fun removeContact(position: Int) {
        (context.applicationContext as App)
                .dbHelper
                .writableDatabase
                .delete("contacts", "" + getUsersFromDB()[position].id + " =id", null)
    }

    override fun getUsersFromDB(): List<Contact> {
        val cursor = (context.applicationContext as App)
                .dbHelper
                .readableDatabase
                .query("contacts", null, null, null, null, null, null)
        if (cursor != null) {
            val idIndex = cursor.getColumnIndex("id")
            val indexName = cursor.getColumnIndex("name")
            val indexIsPhone = cursor.getColumnIndex("isPhone")
            val indexData = cursor.getColumnIndex("data")
            val contactList = mutableListOf<Contact>()
            while (cursor.moveToNext()) {
                contactList.add(Contact().apply {
                    name = cursor.getString(indexName)
                    isPhone = cursor.getInt(indexIsPhone)
                    data = cursor.getString(indexData)
                    id = cursor.getInt(idIndex)
                })
            }
            cursor.close()
            return contactList
        }
        return emptyList()
    }

}