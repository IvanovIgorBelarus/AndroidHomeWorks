package by.itacademy.homework10.model

import android.content.ContentUris

class UriModelMapper : (ResolverModel) -> UriModel {
    override fun invoke(item: ResolverModel) =
        UriModel(
                uri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, item.id),
                title = item.title
        )

}