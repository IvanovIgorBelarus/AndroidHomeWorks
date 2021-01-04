package by.itacademy.homework10.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.homework10.data.MyContentResolver
import by.itacademy.homework10.model.MusicModel
import by.itacademy.homework10.model.MusicModelMapper

class MainActivityViewModel : ViewModel() {
    private val mutableMusicLiveData = MutableLiveData<List<MusicModel>>()
    private val musicModelMapper = MusicModelMapper()
    val musicLiveData = mutableMusicLiveData
    fun getMusicData(context: Context) {
        musicLiveData.value = musicModelMapper.invoke(MyContentResolver.getAllMusic(context))
    }
}