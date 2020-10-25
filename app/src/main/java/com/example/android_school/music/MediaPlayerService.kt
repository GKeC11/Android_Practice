package com.example.android_school.music

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import com.example.module_common.BaseBinder
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MediaPlayerService : Service() {
    val binder = MediaPlayerBinder()
    lateinit var mediaPlayer: MediaPlayer
    val executor = Executors.newSingleThreadScheduledExecutor()

    override fun onCreate() {
        super.onCreate()

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

            setOnPreparedListener {
                start()
                executor.scheduleAtFixedRate({
                    val position = mediaPlayer.currentPosition
                    EventBus.getDefault().post(PositionEvent(position))
                }, 0, 1000, TimeUnit.MILLISECONDS)
            }

        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    fun prepareAsync(uri: Uri) {
        stop()
        mediaPlayer.reset()

        mediaPlayer.apply {
            setDataSource(applicationContext, uri)
            prepareAsync()
        }
    }

    fun play(uri: Uri) {
        prepareAsync(uri)
    }

    fun resume(){
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun stop() {
        mediaPlayer.stop()
    }

    inner class MediaPlayerBinder : BaseBinder() {

        override fun getService(): MediaPlayerService {
            return this@MediaPlayerService
        }
    }
}