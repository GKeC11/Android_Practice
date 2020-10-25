package com.example.module_common

import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenHelper {
    companion object{
        val URI_HEAD = "guet://android/"
        val RECV_ACTIVITY = URI_HEAD + "recv"
        val COUNTER_ACTIVITY = URI_HEAD + "counter"
        val LOGIN_ACTIVITY = URI_HEAD + "login"
        val NEWS_ACTIVITY = URI_HEAD + "news"
        val MUSIC_ACTIVITY = URI_HEAD + "music"

        fun open( context:Context, uri: String){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context?.startActivity(intent)
        }
    }
}