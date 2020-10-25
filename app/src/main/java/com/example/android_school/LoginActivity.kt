package com.example.android_school

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.android_school.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.holder = LoginHolder(binding)
    }
}

class LoginHolder(var binding:ActivityLoginBinding?): BaseObservable() {
    val MODE_TEXTPASSWORD = 0x00000081
    val MODE_TEXT = 0x00000001

    @get:Bindable
    var isHide = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.hide)
        }

    fun changeVisibility(){
        isHide = !isHide
    }
}