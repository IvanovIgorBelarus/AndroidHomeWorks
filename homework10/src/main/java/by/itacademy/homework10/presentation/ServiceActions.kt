package by.itacademy.homework10.presentation

import by.itacademy.homework10.model.MusicModel

interface ServiceActions {
    fun getPlayList(list: List<MusicModel>)
    fun startMusic()
    fun stopMusic()
    fun pauseMusic()

}