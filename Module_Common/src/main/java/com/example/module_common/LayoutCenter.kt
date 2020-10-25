package com.example.module_common

import android.content.Context

class LayoutCenter {

    companion object{
        var context: Context? = null
        var bean2ItemMap = hashMapOf<String, String>()

        fun init(context: Context){
            Companion.context = context
        }

        fun build(bean: BaseBean): BaseItem {
            val itemName = bean2ItemMap.get(bean.javaClass.simpleName)
            val item = Class.forName(itemName)
            return item.getConstructor(android.content.Context::class.java).newInstance(
                context
            ) as BaseItem
        }

        fun register(bean: String, item: String){
            bean2ItemMap.put(bean, item)
        }
    }

}