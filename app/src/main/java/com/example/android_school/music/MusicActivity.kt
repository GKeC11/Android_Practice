package com.example.android_school.music

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module_common.ServiceManager
import com.example.android_school.databinding.ActivityMusicBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MusicActivity : AppCompatActivity() {
    var binding: ActivityMusicBinding? = null
    var service: MediaPlayerService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        EventBus.getDefault().register(this)
        service = ServiceManager.findService(MediaPlayerService::class.java.simpleName) as MediaPlayerService

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun notifyBottomBar(musicEvent: MusicEvent){
        service?.play(musicEvent.music.uri)
        binding!!.musicBottomBar.setMusicInfo(musicEvent.music)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun notifyProgressBar(positionEvent: PositionEvent){
        binding!!.musicBottomBar.updateProgress(positionEvent.position)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun notifyMediaPlayer(stateEvent: StateEvent){
        if(stateEvent.isPlay){
            service?.resume()
        }else{
            service?.pause()
        }
    }
}