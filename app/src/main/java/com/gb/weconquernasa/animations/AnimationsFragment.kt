package com.gb.weconquernasa.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {

    var isOpen: Boolean = false

    private var _binding: FragmentAnimationsBinding? = null
    private val binding: FragmentAnimationsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.recyclerView.adapter = Adapter()

    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_animations_recycler_item, parent, false) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener { button ->
                val epicenterExplode = Rect()
                button.getGlobalVisibleRect(epicenterExplode)
                val transitionExplode = Explode()
                transitionExplode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return epicenterExplode
                    }
                }
                transitionExplode.duration = 2500
                transitionExplode.excludeTarget(button, true)
                val transitionFade = Fade().addTarget(button).setDuration(3000)
                val transitionSet = TransitionSet().addTransition(transitionExplode).addTransition(transitionFade)
                TransitionManager.beginDelayedTransition(binding.recyclerView, transitionSet)
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount(): Int {
            return 24
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimationsFragment()
    }

}
