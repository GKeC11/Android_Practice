package com.example.module_common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T: BaseBean> : RecyclerView.Adapter<BaseViewHolder>(){
    var item: BaseItem? = null
    var beans: MutableList<T>? = mutableListOf()
    var binding: ViewDataBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), item!!.getLayoutId(), parent, false)
        return BaseViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val bean = beans?.get(position) as BaseBean
        item?.onBindViewHolder(holder, position, bean)
    }

    override fun getItemCount(): Int {
        return beans!!.size
    }
}

class BaseViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    var root = root
}