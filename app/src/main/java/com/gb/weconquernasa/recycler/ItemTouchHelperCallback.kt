package com.gb.weconquernasa.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: RecyclerFragmentAdapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.omItemDismiss(viewHolder.adapterPosition)
    }
}