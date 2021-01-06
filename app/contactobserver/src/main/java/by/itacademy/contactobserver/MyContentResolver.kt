package by.itacademy.contactobserver

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri

class MyContentResolver {
    companion object {
        @SuppressLint("Recycle")
        fun getContacts(context: Context): List<Contact> {
            val result = mutableListOf<Contact>()
            val uri = Uri.parse("content://by.itacademy.contacts/contacts")
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                val idName = cursor.getColumnIndex("name")
                val idData = cursor.getColumnIndex("data")
                val idIsPhone = cursor.getColumnIndex("isPhone")
                while (cursor.moveToNext()) {
                    result.add(Contact(cursor.getString(idName), cursor.getString(idData), cursor.getInt(idIsPhone)))
                }
                return result
            }
            return emptyList()
        }
    }
}