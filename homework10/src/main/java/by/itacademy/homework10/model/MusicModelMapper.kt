package by.itacademy.homework10.model

class MusicModelMapper: (Map<Long,String>)->List<MusicModel> {
    override fun invoke(map: Map<Long, String>) = map.map { item->
        MusicModel(
                musicId = item.key,
                musicTitle = item.value
        )
    }
}