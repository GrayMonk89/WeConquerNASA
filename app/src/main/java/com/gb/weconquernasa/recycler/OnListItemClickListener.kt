package com.gb.weconquernasa.recycler

interface OnListItemClickListener {
    fun onItemClick(data: Data)
    fun onAddBtnClick(data: Pair<Data, Boolean>, position: Int)
    fun onRemoveBtnClick(position: Int)
    fun moveItemUp(position: Int)
    fun moveItemDown(position: Int)
}