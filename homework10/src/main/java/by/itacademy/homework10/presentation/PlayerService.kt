package by.itacademy.homework10.presentation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import by.itacademy.homework10.R
import by.itacademy.homework10.TAG
import by.itacademy.homework10.data.MyContentResolver
import by.itacademy.homework10.model.ResolverModel
import by.itacademy.homework10.model.UriModelMapper

private const val NOTIFY_ID = 101
private const val CHANNEL_ID = "My channel"

class PlayerService : Service(), ServiceActions {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playList: List<ResolverModel>
    private lateinit var musicListener: MusicListener
    private val uriMapper = UriModelMapper()
    private var i = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        playList = MyContentResolver.getAllMusic(applicationContext)
        createPlayer()
        createNotification(playList[0].title)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = PlayerBinder()
    inner class PlayerBinder : Binder() {
        fun getPlayerActions(listener: MusicListener): ServiceActions {
            musicListener = listener
            return this@PlayerService
        }

    }

    override fun startMusic() {
        musicListener.onSongChange(uriMapper.invoke(playList[0]).title)
        mediaPlayer.start()
    }

    override fun stopMusic() {
        mediaPlayer.stop()
    }

    override fun pauseMusic() {
        mediaPlayer.pause()
    }

    override fun playChosenSong(title: String) {
        musicListener.onSongChange(title)
        for (n in i..playList.size) {
            if (playList[n].title == title) {
                val uri = uriMapper.invoke(playList[n]).uri
                i=n
                createNotification(title)
                with(mediaPlayer) {
                    reset()
                    setDataSource(this@PlayerService.applicationContext, uri)
                    prepare()
                    start()
                }
                break
            }
        }
    }

    private fun createPlayer() {
        if (playList.isNotEmpty()) {
            val uri = uriMapper.invoke(playList[i]).uri
            mediaPlayer = MediaPlayer.create(applicationContext, uri)
            mediaPlayer.setOnCompletionListener { mp ->
                               mediaPlayer.reset()
                if (i < playList.size - 1) {
                    i++
                    Log.d(TAG, "i=$i")
                    createNotification(playList[i].title)
                    playChosenSong(playList[i].title)
                } else {
                    createNotification("playlist ended")
                    i = 0
                }
            }
        }
    }

    private fun createNotification(title: String) {
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_play_circle_outline_24)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notificationManager.notify(NOTIFY_ID, notification)
    }

}