package com.gb.weconquernasa.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentRecyclerBinding
import com.gb.weconquernasa.databinding.FragmentRecyclerItemEarthBinding
import com.gb.weconquernasa.databinding.FragmentRecyclerItemMarsBinding
import com.gb.weconquernasa.databinding.FragmentRecyclerItemSunBinding
import com.gb.weconquernasa.utils.BaseViewHolder
import com.gb.weconquernasa.utils.EARTH_DEFAULT_VALUE
import com.gb.weconquernasa.utils.MARS_DEFAULT_VALUE
import com.gb.weconquernasa.utils.SUN_DEFAULT_VALUE

class RecyclerFragmentAdapter(private var list: List<Data>) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SUN_DEFAULT_VALUE -> {
                val view = FragmentRecyclerItemSunBinding.inflate(LayoutInflater.from(parent.context))
                SunViewHolder(view.root)
            }

            EARTH_DEFAULT_VALUE -> {
                val view = FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }

            MARS_DEFAULT_VALUE -> {
                val view =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
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

    class SunViewHolder(view: View) :BaseViewHolder(view) {
        override fun bindAttribute(data:Data){
            (FragmentRecyclerItemEarthBinding.bind(itemView)).apply {
                title.text =data.someText
                descriptionTextView.text = data.someDescription
            }
        }
    }
    class EarthViewHolder(view: View) :BaseViewHolder(view) {
        override fun bindAttribute(data:Data){
            (FragmentRecyclerItemEarthBinding.bind(itemView)).apply {
                title.text =data.someText
                descriptionTextView.text = data.someDescription
            }
        }
    }

    class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bindAttribute(data:Data){
            (FragmentRecyclerItemMarsBinding.bind(itemView)).apply {
                title.text =data.someText
                descriptionTextView.text = data.someDescription
            }
        }
    }

}