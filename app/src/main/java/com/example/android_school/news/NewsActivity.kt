package com.example.android_school.news

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BaseObservable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_school.databinding.ActivityNewsBinding
import com.example.module_common.BaseBeanAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NewsActivity : AppCompatActivity() {
    var binding: ActivityNewsBinding? = null
    var newsAdapter = BaseBeanAdapter<NewsBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        requestNews()

        binding?.rvNews?.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            adapter = newsAdapter
        }
    }

    fun requestNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://v.juhe.cn/toutiao/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = retrofit.create(GetNews::class.java)
        val call = request.getCall

        call.enqueue(object : Callback<NewsClass> {
            override fun onResponse(call: Call<NewsClass>, response: Response<NewsClass>) {
                val news = response.body() as NewsClass

                val beans = mutableListOf<NewsBean>()
                news?.result?.data?.forEach {
                    val bean = NewsBean(
                        it.thumbnail_pic_s!!,
                        it.title!!,
                        it.author_name!!
                    )
                    beans.add(bean)
                }

                newsAdapter.addBeans(beans)
            }

            override fun onFailure(call: Call<NewsClass>, t: Throwable) {
                Log.d("Run", "onFailure: request failed: " + t)
            }
        })
    }
}

class NewsHolder() : BaseObservable() {

}

interface GetNews {
    @get:GET("index?type=&key=f2209f419b0103e8e4237b4f3db28a6b")
    val getCall: Call<NewsClass>
}