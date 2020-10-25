package com.example.android_school

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_school.databinding.ActivityRecvBinding

class RecvActivity : AppCompatActivity() {
    var binding: ActivityRecvBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecvBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val data = getIntent().data
        val name = data?.getQueryParameter("name")
        binding?.tvDisplay?.text = name
    }
}