package com.gb.weconquernasa.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gb.weconquernasa.recycler.Data

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindAttribute(data: Pair<Data, Boolean>)
}