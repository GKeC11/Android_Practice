package com.example.android_school

import android.app.Application
import com.example.android_school.music.MusicBean
import com.example.android_school.music.MusicItem
import com.example.android_school.news.NewsBean
import com.example.android_school.news.NewsItem
import com.example.module_common.LayoutCenter

class CoreApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        LayoutCenter.init(this)
        LayoutCenter.register(NewsBean::class.java.simpleName, NewsItem::class.java.name)
        LayoutCenter.register(MusicBean::class.java.simpleName, MusicItem::class.java.name)
    }
}