package by.itacademy.homework10.presentation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
private const val ACTION_PLAY="presentation.MediaPlayerService"
@Suppress("UNREACHABLE_CODE")
class MediaPlayerService : Service(), MediaPlayer.OnPreparedListener {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when(intent.action){
            ACTION_PLAY->{
                mediaPlayer= MediaPlayer.create(this,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
                mediaPlayer.apply {
                    setOnPreparedListener(this@MediaPlayerService)
                    prepareAsync()
                }
            }
        }
        return START_STICKY
    }
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}