package by.itacademy.contacts.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import by.itacademy.contacts.App

class MyContentProvider : ContentProvider() {
    private lateinit var db: SQLiteDatabase
    override fun onCreate(): Boolean {
        db = (context as App)
                .dbHelper
                .writableDatabase
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, sortOrder)
                cursor.setNotificationUri(context?.contentResolver, uri)
                cursor
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            DATA -> "object/uri"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val id = db.insert(TABLE_NAME, null, values)
                context?.contentResolver?.notifyChange(uri, null)
                Uri.parse("$TABLE_NAME/$id")
            }
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d("qwe","sel=$selection, selArg=${selectionArgs?.get(0)}")
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val rowDeleted = db.delete(TABLE_NAME, selection, selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                rowDeleted
            }
            else -> -1
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val rowUpdated = db.update(TABLE_NAME, values, selection, selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                rowUpdated
            }
            else -> -1
        }
    }

    companion object {
        private const val AUTHORITY = "by.itacademy.contacts"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/contacts")
        private const val DATA = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, TABLE_NAME, DATA)
        }
    }
}