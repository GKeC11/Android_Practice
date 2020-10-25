package com.example.android_school.news

class NewsClass {
    var result: Result? = null
}

class Result{
    var data: ArrayList<Data>? = null
}

class Data {
    var title: String? = null
    var author_name: String? = null
    var thumbnail_pic_s: String? = null
}