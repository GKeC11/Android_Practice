package com.example.android_school.news

import android.content.Context
import com.bumptech.glide.Glide
import com.example.android_school.R
import com.example.module_common.BaseBean
import com.example.module_common.BaseItem
import com.example.module_common.BaseViewHolder
import kotlinx.android.synthetic.main.item_news.view.*

class NewsItem(context: Context) : BaseItem {
    override var context = context

    override fun getLayoutId(): Int {
        return R.layout.item_news
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, bean: BaseBean) {
        val view = holder.root
        val newsBean = bean as NewsBean
        view.tv_title.text = newsBean.title
        view.tv_author.text = newsBean.author
        Glide.with(context).load(bean.img).centerCrop().into(view.iv_news)
    }
}