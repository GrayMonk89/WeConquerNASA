package com.gb.weconquernasa.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: RecyclerFragmentAdapter): ItemTouchHelper.Callback() {
    //class ItemTouchHelperCallback(private val itemTouchHelperAdapter: ItemTouchHelperAdapter): ItemTouchHelper.Callback() {
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
        //itemTouchHelperAdapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.omItemDismiss(viewHolder.adapterPosition)
        //itemTouchHelperAdapter.omItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {

        when (viewHolder) {
            is RecyclerFragmentAdapter.MarsViewHolder -> {
                viewHolder.onItemSelected()
            }
            is RecyclerFragmentAdapter.SunViewHolder -> {
                viewHolder.onItemSelected()
            }
            is RecyclerFragmentAdapter.EarthViewHolder -> {
                viewHolder.onItemSelected()
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        when (viewHolder) {
            is RecyclerFragmentAdapter.MarsViewHolder -> {
                viewHolder.onItemClear()
            }
            is RecyclerFragmentAdapter.SunViewHolder -> {
                viewHolder.onItemClear()
            }
            is RecyclerFragmentAdapter.EarthViewHolder -> {
                viewHolder.onItemClear()
            }
        }
        super.clearView(recyclerView, viewHolder)
    }


}