package com.example.android_school.music

import android.net.Uri
import com.example.module_common.BaseBean

class MusicBean(id:Long, name: String, author: String, duration: Int, size: Int, uri: Uri) :
    BaseBean {
    var id: Long = id
    var name: String = name
    var author: String = author
    var duration: Int = duration
    var size: Int = size
    var uri: Uri = uri
}