package by.itacademy.contacts

import android.content.ContentValues
import android.content.Context

class DBOperationsImpl : DBOperations {
    override fun saveContactInDB(context: Context, contact: Contact) {
        val contentValues = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        (context as App)
                .dbHelper
                .writableDatabase
                .insert("contacts", null, contentValues)
    }
    override fun changeContact(context: Context,contact: Contact, position: Int) {
        val contentValue = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        (context as App)
                .dbHelper
                .writableDatabase
                .update("contacts", contentValue, "" + getUsersFromDB(context)[position].id + " =id", null)
    }
    override fun removeContact(context: Context, position: Int) {
        (context as App)
                .dbHelper
                .writableDatabase
                .delete("contacts", "" + getUsersFromDB(context)[position].id + " =id", null)
    }
    override fun getUsersFromDB(context: Context): List<Contact> {
        val cursor = (context as App)
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