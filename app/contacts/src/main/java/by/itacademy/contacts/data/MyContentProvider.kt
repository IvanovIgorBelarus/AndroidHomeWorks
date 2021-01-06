package by.itacademy.contacts.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import by.itacademy.contacts.App

class MyContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            DATA -> (context as App)
                    .dbHelper
                    .writableDatabase
                    .query(TABLE_NAME, null, selection, selectionArgs, null, null, sortOrder)
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
                val id = (context as App)
                        .dbHelper
                        .writableDatabase
                        .insert(TABLE_NAME, null, values)
                ContentUris.withAppendedId(uri, id)
            }
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                (context as App)
                        .dbHelper
                        .writableDatabase
                        .delete(TABLE_NAME, selection, selectionArgs)
            }
            else -> -1
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                (context as App)
                        .dbHelper
                        .writableDatabase
                        .update(TABLE_NAME, values, selection, selectionArgs)
            }
            else -> -1
        }
    }

    companion object {
        private const val CONTENT_AUTHORITY = "by.itacademy.contacts"
        private val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")
        val CONTENT_URI: Uri = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME)
        private const val DATA = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(CONTENT_AUTHORITY, "data/data/#", DATA)
        }
    }
}