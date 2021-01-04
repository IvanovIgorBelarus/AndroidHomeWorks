package by.itacademy.homework10.data

import android.content.Context
import android.util.Log
import by.itacademy.homework10.TAG
import by.itacademy.homework10.model.ResolverModel

class MyContentResolver {
    companion object {
        fun getAllMusic(context: Context): List<ResolverModel> {
            val result = mutableListOf<ResolverModel>()
            val resolver = context.contentResolver
            val uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val cursor = resolver.query(uri, null, null, null, null)
            if (cursor != null) {
                val idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
                val titleColumn: Int = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
                while (cursor.moveToNext()) {
                    result.add(ResolverModel(cursor.getLong(idColumn),cursor.getString(titleColumn)))
                }
            } else {
                Log.d(TAG, "cursor null")
                return emptyList()
            }
            cursor.close()
            return result.toList()
        }
    }

}