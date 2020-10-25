package com.example.android_school.news

import com.example.module_common.BaseBean

class NewsBean(img: String, title: String, author: String):
    BaseBean {
    var img: String? = img
    var title: String? = title
    var author: String? = author
}