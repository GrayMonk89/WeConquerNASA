package com.gb.weconquernasa.recycler.recycler_interface

import java.text.FieldPosition

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun omItemDismiss(position: Int)
}

interface ItemTouchHelperViewHolder {
    fun onItemSelected()
    fun onItemClear()
}