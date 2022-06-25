package com.gb.weconquernasa.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.*
import com.gb.weconquernasa.utils.*

class RecyclerFragmentAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var list: MutableList<Data>

    fun setList(newList: List<Data>) {
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: List<Data>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: List<Data>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
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
        override fun bindAttribute(data: Data) {
            (FragmentRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = data.someText
            }
        }

    }

    inner class SunViewHolder(view: View) : BaseViewHolder(view) {
        override fun bindAttribute(data: Data) {
            (FragmentRecyclerItemSunBinding.bind(itemView)).apply {
                title.text = data.someText
                descriptionTextView.text = data.someDescription
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
            }
        }
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bindAttribute(data: Data) {
            (FragmentRecyclerItemEarthBinding.bind(itemView)).apply {
                title.text = data.someText
                descriptionTextView.text = data.someDescription
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(data, layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bindAttribute(data: Data) {
            (FragmentRecyclerItemMarsBinding.bind(itemView)).apply {
                title.text = data.someText
                descriptionTextView.text = data.someDescription
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
            }
        }
    }
}