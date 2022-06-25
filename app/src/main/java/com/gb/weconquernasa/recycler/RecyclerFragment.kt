package com.gb.weconquernasa.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentRecyclerBinding
import com.gb.weconquernasa.utils.*
import kotlin.math.E

class RecyclerFragment : Fragment(), OnListItemClickListener {


    private lateinit var adapter: RecyclerFragmentAdapter
    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() = _binding!!


    private val list = mutableListOf(
        Data(HEADER_DEFAULT_NAME, EMPTY_DEFAULT_DESCRIPTION, HEADER_DEFAULT_VALUE),
        Data(SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE),
        Data(EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
        Data(SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE),
        Data(EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
        Data(EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
        Data(MARS_DEFAULT_NAME, MARS_DEFAULT_DESCRIPTION, MARS_DEFAULT_VALUE),
        Data(EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
        Data(SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE),
        Data(EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE),
        Data(MARS_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, MARS_DEFAULT_VALUE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerFragmentAdapter(this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun onItemClick(data: Data) {

    }

    override fun onAddBtnClick(data: Data, position: Int) {

        when (data.type) {
            SUN_DEFAULT_VALUE -> {
                list.add(position, Data("Sun", "Sun des", SUN_DEFAULT_VALUE))
            }
            EARTH_DEFAULT_VALUE -> {
                list.add(position, Data("Earth", "Earth des", EARTH_DEFAULT_VALUE))
            }
            MARS_DEFAULT_VALUE -> {
                list.add(position, Data("Mars", "Mars des", MARS_DEFAULT_VALUE))
            }
        }
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }

    override fun moveItemUp(position: Int) {
        if (position != 1) {
            list.removeAt(position).apply {
                list.add(position - 1, this)
            }
            adapter.notifyItemMoved(position, position - 1)
        }
    }

    override fun moveItemDown(position: Int) {
        if (position != list.size-1) {
            list.removeAt(position).apply {
                list.add(position + 1, this)
            }
            adapter.notifyItemMoved(position, position + 1)
        }
    }
}