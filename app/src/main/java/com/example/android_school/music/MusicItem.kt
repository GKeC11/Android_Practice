package com.example.android_school.music

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.module_common.BaseBean
import com.example.module_common.BaseItem
import com.example.module_common.BaseViewHolder
import com.example.android_school.R
import kotlinx.android.synthetic.main.item_music.view.*
import org.greenrobot.eventbus.EventBus

class MusicItem(context: Context) : BaseItem {
    override var context = context
    lateinit var view: View

    override fun getLayoutId(): Int {
        return R.layout.item_music
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, bean: BaseBean) {
        view = holder.root

        val music = bean as MusicBean
        view.tv_music_name.apply {
            text = music.name
            paint.isFakeBoldText = true
        }
        view.tv_music_author.text = music.author
        view.tv_order.apply {
            setTextColor(resources.getColor(R.color.colorBlueLight, null))
            text = position.toString()
        }

        setClickEvent(bean)
    }

    fun setClickEvent(bean: MusicBean){
        view.setOnClickListener {
            EventBus.getDefault().post(MusicEvent(bean))
        }
    }

}