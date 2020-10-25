package com.example.module_common

class BaseBeanAdapter<T: BaseBean>() : BaseAdapter<T>(){

    fun addBeans(beans: MutableList<T>){
        this.beans = beans
        if(beans.size > 0){
            addItem()
        }
        notifyDataSetChanged()
    }

    fun addItem(){
        val item = LayoutCenter.build(beans!!.first())
        this.item = item
    }
}