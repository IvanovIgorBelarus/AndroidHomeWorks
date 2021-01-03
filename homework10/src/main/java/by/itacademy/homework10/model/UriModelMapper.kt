package by.itacademy.homework10.model

import android.content.ContentUris

class UriModelMapper : (Map<Long,String>) -> List<UriModel> {
    override fun invoke(musicModel: Map<Long,String>) = musicModel.map { item ->
        UriModel(
                uri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, item.key)
        )
    }
}