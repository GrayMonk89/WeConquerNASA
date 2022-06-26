package com.gb.weconquernasa.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.*
import com.gb.weconquernasa.recycler.recycler_interface.ItemTouchHelperAdapter
import com.gb.weconquernasa.recycler.recycler_interface.ItemTouchHelperViewHolder
import com.gb.weconquernasa.recycler.recycler_interface.OnListItemClickListener
import com.gb.weconquernasa.utils.*

class RecyclerFragmentAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    private lateinit var list: MutableList<Pair<Data, Boolean>>

    fun setList(newList: MutableList<Pair<Data, Boolean>>) {
        this.list = newList
    }

    fun setAddToList(newList: MutableList<Pair<Data, Boolean>>, position: Int) {
        this.list = newList
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: MutableList<Pair<Data, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].first.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SUN_DEFAULT_VALUE -> {
                val view =
                    FragmentRecyclerItemSunBinding.inflate(LayoutInflater.from(parent.context))
                SunViewHolder(view.root)
            }

            EARTH_DEFAULT_VALUE -> {
                val view =
                    FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }

            MARS_DEFAULT_VALUE -> {
                val view =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
            }

            HEADER_DEFAULT_VALUE -> {
                val view =
                    FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(view.root)
            }

            else -> {
                val view = FragmentRecyclerBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindAttribute(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bindAttribute(data: Pair<Data, Boolean>) {
            (FragmentRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = data.first.someText
            }
        }
    }

    inner class SunViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bindAttribute(data: Pair<Data, Boolean>) {
            (FragmentRecyclerItemSunBinding.bind(itemView)).apply {
                title.text = data.first.someText
                descriptionTextView.text = data.first.someDescription
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(data, layoutPosition)
                }

                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }

                moveItemUp.setOnClickListener {
                    if (layoutPosition != 1) {
                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition - 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }
                moveItemDown.setOnClickListener {
                    if (layoutPosition != list.size-1) {
                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }
                sunImageView.setOnClickListener { it ->
                    list[layoutPosition] = list[layoutPosition].let {
                        it.first to !it.second
                    }
                    marsDescriptionTextView.text = it.context.getText(R.string.sun_description)
                    marsDescriptionTextView.visibility = if(list[layoutPosition].second) View.VISIBLE else View.GONE
                }
            }
        }
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bindAttribute(data: Pair<Data, Boolean>) {
            (FragmentRecyclerItemEarthBinding.bind(itemView)).apply {
                title.text = data.first.someText
                descriptionTextView.text = data.first.someDescription
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(data, layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bindAttribute(data: Pair<Data, Boolean>) {
            (FragmentRecyclerItemMarsBinding.bind(itemView)).apply {
                title.text = data.first.someText
                descriptionTextView.text = data.first.someDescription
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(data, layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
                moveItemUp.setOnClickListener {
                    onListItemClickListener.moveItemUp(layoutPosition)
                }
                moveItemDown.setOnClickListener {
                    onListItemClickListener.moveItemDown(layoutPosition)
                }
                marsImageView.setOnClickListener { it ->
                    list[layoutPosition] = list[layoutPosition].let {
                        it.first to !it.second
                    }
                    marsDescriptionTextView.text = it.context.getText(R.string.mars_description)
                    marsDescriptionTextView.visibility = if(list[layoutPosition].second) View.VISIBLE else View.GONE
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        onListItemClickListener.onItemMove(fromPosition,toPosition)
    }

    override fun omItemDismiss(position: Int) {
        onListItemClickListener.omItemDismiss(position)
    }
}