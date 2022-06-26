package com.gb.weconquernasa.recycler.recycler_interface

import com.gb.weconquernasa.recycler.Data

interface OnListItemClickListener {
    fun onItemClick(data: Data)
    fun onAddBtnClick(data: Pair<Data, Boolean>, position: Int)
    fun onRemoveBtnClick(position: Int)
    fun moveItemUp(position: Int)
    fun moveItemDown(position: Int)
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun omItemDismiss(position: Int)
}