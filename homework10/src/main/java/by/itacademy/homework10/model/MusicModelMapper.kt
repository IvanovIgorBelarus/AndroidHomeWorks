package by.itacademy.homework10.model

class MusicModelMapper: (List<ResolverModel>)->List<MusicModel> {
    override fun invoke(list: List<ResolverModel>) = list.map { item->
        MusicModel(
                musicId = item.id,
                musicTitle = item.title
        )
    }
}