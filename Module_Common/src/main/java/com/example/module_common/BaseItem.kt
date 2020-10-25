package com.example.module_common

import android.content.Context
import com.example.module_common.BaseBean
import com.example.module_common.BaseViewHolder

interface BaseItem {
    var context: Context
    fun getLayoutId(): Int
    fun onBindViewHolder(holder: BaseViewHolder, position: Int, bean: BaseBean)
}