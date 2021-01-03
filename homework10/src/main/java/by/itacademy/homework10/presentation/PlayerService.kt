package by.itacademy.homework10.presentation

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import by.itacademy.homework10.TAG
import by.itacademy.homework10.data.MyContentResolver
import by.itacademy.homework10.model.MusicModel
import by.itacademy.homework10.model.UriModelMapper

class PlayerService : Service(), ServiceActions {
    private lateinit var mediaPlayer: MediaPlayer
    private val uriMapper = UriModelMapper()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand")
        val playList = MyContentResolver.getAllMusic(this.applicationContext)
        val uri: Uri = uriMapper.invoke(playList)[0].uri
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                    AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            )
            setDataSource(this@PlayerService.applicationContext, uri)
            prepareAsync()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = PlayerBinder()

    inner class PlayerBinder : Binder() {
        fun getPlayerActions(): ServiceActions = this@PlayerService
    }

    override fun getPlayList(list: List<MusicModel>) {
//        Log.d(TAG, "getPlayList")
//        playList.addAll(list)
    }

    override fun startMusic() {
        Log.d(TAG, "Start Music")
        mediaPlayer.start()
    }

    override fun stopMusic() {
        Log.d(TAG, "Stop Music")
        mediaPlayer.stop()
    }

    override fun pauseMusic() {
        Log.d(TAG, "Pause Music")
        mediaPlayer.pause()
    }

}