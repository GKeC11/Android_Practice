package com.example.android_school

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.android_school.databinding.ActivityMainBinding
import com.example.android_school.music.MediaPlayerService
import com.example.module_common.OpenHelper
import com.example.module_common.ServiceManager
import org.greenrobot.eventbus.EventBus


class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ServiceManager.register(this, MediaPlayerService::class.java)

        binding?.bSend?.setOnClickListener {
            var uri = OpenHelper.RECV_ACTIVITY + "?name=" + binding?.etText?.text
            Toast.makeText(this, binding?.etText?.text, Toast.LENGTH_SHORT).show()
            OpenHelper.open(this, uri)
        }

        binding?.bCounter?.setOnClickListener {
            OpenHelper.open(this, OpenHelper.COUNTER_ACTIVITY)
        }

        binding?.bLogin?.setOnClickListener {
            OpenHelper.open(this, OpenHelper.LOGIN_ACTIVITY)
        }

        binding?.bNews?.setOnClickListener {
            OpenHelper.open(this, OpenHelper.NEWS_ACTIVITY)
        }

        binding?.bMusic?.setOnClickListener {
            OpenHelper.open(this, OpenHelper.MUSIC_ACTIVITY)
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}