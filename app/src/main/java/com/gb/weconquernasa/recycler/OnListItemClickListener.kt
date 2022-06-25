package com.gb.weconquernasa.recycler

interface OnListItemClickListener {
    fun onItemClick(data: Data)
    fun onAddBtnClick(data: Data, position: Int)
    fun onRemoveBtnClick(position: Int)
}