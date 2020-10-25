package com.example.android_school.music

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Size
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.android_school.BR
import com.example.android_school.R
import com.example.android_school.databinding.MusicBottomBarBinding
import org.greenrobot.eventbus.EventBus


class BottomBar : FrameLayout{
    var binding: MusicBottomBarBinding? = null

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        binding = MusicBottomBarBinding.inflate(LayoutInflater.from(context), this, true)
        binding?.holder = MusicStateHolder(binding!!)
        binding!!.tvMusicTitle.apply {
            paint.isFakeBoldText = true
        }

        Glide.with(context)
            .load(R.drawable.ic_default_music)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding!!.ivMusicThumbnail)

        binding?.pbMusicProgress?.progress = 100 //有存储最后播放功能后 修改此段
    }

    fun setMusicInfo(music: MusicBean){
        var thumbnail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver.loadThumbnail(Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, music.id.toString()), Size(640,640), null)
        } else {
            var uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, music.id.toString())
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

        Glide.with(context)
            .load(thumbnail)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(binding!!.ivMusicThumbnail)

        binding?.apply {
            tvMusicTitle.text = music.name
            tvMusicAuthor.text = music.author
            pbMusicProgress.progress = 0
            pbMusicProgress.max = music.duration
            holder?.isPlay = true
        }
    }

    fun updateProgress(position: Int){
        binding!!.pbMusicProgress.progress = position
    }
}

class MusicStateHolder(binding: MusicBottomBarBinding) : BaseObservable() {
    @get:Bindable
    var isPlay = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.play)
        }

    fun onButtonClick(){
        isPlay = !isPlay
        EventBus.getDefault().post(StateEvent(isPlay))
    }
}