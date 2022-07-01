package com.gb.weconquernasa.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.gb.weconquernasa.databinding.FragmentRecyclerBinding
import com.gb.weconquernasa.recycler.recycler_interface.OnListItemClickListener
import com.gb.weconquernasa.utils.*

class RecyclerFragment : Fragment(), OnListItemClickListener {

    private var isNewList = false

    private lateinit var adapter: RecyclerFragmentAdapter
    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() = _binding!!


    private val list = mutableListOf(
        Pair(Data(0,HEADER_DEFAULT_NAME, EMPTY_DEFAULT_DESCRIPTION, HEADER_DEFAULT_VALUE), false),
        Pair(Data(1,SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE), false),
        Pair(Data(2,EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE), false),
        Pair(Data(3,SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE), false),
        Pair(Data(4,EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE), false),
        Pair(Data(5,EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE), false),
        Pair(Data(6,MARS_DEFAULT_NAME, MARS_DEFAULT_DESCRIPTION, MARS_DEFAULT_VALUE), false),
        Pair(Data(7,EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE), false),
        Pair(Data(8,SUN_DEFAULT_NAME, SUN_DEFAULT_DESCRIPTION, SUN_DEFAULT_VALUE), false),
        Pair(Data(9,EARTH_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, EARTH_DEFAULT_VALUE), false),
        Pair(Data(10,MARS_DEFAULT_NAME, EARTH_DEFAULT_DESCRIPTION, MARS_DEFAULT_VALUE), false)
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

        binding.recyclerActivityDiffUtilFAB.setOnClickListener {
            changeAdapterData()
        }

        setAdapter()
    }

    private fun changeAdapterData() {
        adapter.setList(createItemList(isNewList).map { it })
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): List<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> listOf(
                Pair(Data(0, "Header"), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Mars", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Mars", ""), false),
                Pair(Data(5, "Mars", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
            true -> listOf(
                Pair(Data(0, "Header"), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Jupiter", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Neptune", ""), false),
                Pair(Data(5, "Saturn", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
        }
    }

    private fun setAdapter() {
        adapter = RecyclerFragmentAdapter(list, this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
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

    override fun onAddBtnClick(data: Pair<Data, Boolean>, position: Int) {

        when (data.first.type) {
            SUN_DEFAULT_VALUE -> {
                list.add(position, Pair(Data(list.size+1,"Sun", "Sun des", SUN_DEFAULT_VALUE), false))
            }
            EARTH_DEFAULT_VALUE -> {
                list.add(position, Pair(Data(list.size+1,"Earth", "Earth des", EARTH_DEFAULT_VALUE),false))
            }
            MARS_DEFAULT_VALUE -> {
                list.add(position, Pair(Data(list.size+1,"Mars", "Mars des", MARS_DEFAULT_VALUE),false))
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
        if (position != list.size - 1) {
            list.removeAt(position).apply {
                list.add(position + 1, this)
            }
            //adapter.setList(list)
            adapter.notifyItemMoved(position, position + 1)

        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (toPosition >= 1 && fromPosition != 0) {
            list.removeAt(fromPosition).apply {
                list.add(toPosition, this)
            }
            adapter.notifyItemMoved(fromPosition, toPosition)
            //setAdapter()
        }
    }

    override fun omItemDismiss(position: Int) {
        list.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}