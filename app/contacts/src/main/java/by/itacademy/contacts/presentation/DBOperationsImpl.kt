package by.itacademy.contacts.presentation

import android.content.ContentValues
import android.content.Context
import by.itacademy.contacts.App
import by.itacademy.contacts.data.Contact
import by.itacademy.contacts.data.MyContentProvider
import by.itacademy.contacts.data.TABLE_NAME

class DBOperationsImpl : DBOperations {
    override fun saveContactInDB(context: Context, contact: Contact) {
        val contentValues = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        context.contentResolver.insert(MyContentProvider.CONTENT_URI, contentValues)
    }

    override fun changeContact(context: Context, contact: Contact, position: Int) {
        val contentValue = ContentValues().apply {
            put("name", contact.name)
            put("isPhone", contact.isPhone)
            put("data", contact.data)
        }
        val selection = getUsersFromDB(context)[position].id
        context.contentResolver.update(MyContentProvider.CONTENT_URI, contentValue, "id = $selection", null)
    }

    override fun removeContact(context: Context, position: Int) {
        val selection = getUsersFromDB(context)[position].id
        context.contentResolver.delete(MyContentProvider.CONTENT_URI,"id = $selection", null)
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