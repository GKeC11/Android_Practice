package com.example.android_school

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_school.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    var binding: ActivityCounterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        binding?.holder = viewModel.holder

        binding?.bShow?.setOnClickListener {
            Toast.makeText(this, viewModel.holder.mCount.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}

class CounterViewModel : ViewModel(){
    val holder = CounterHolder()
}

class CounterHolder : BaseObservable(){
    @get:Bindable
    var mCount = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.mCount)
        }

    fun count(){
        mCount ++
    }
}
