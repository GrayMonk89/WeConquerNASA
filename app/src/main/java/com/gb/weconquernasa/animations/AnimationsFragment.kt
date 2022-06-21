package com.gb.weconquernasa.animations

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        setButtonAnimationListener()

    }

    private fun setButtonAnimationListener() {
        binding.buttonAnimation.setOnClickListener {
            isOpen = !isOpen

            initTransitionManager()

            binding.textForAnimation.visibility = if (isOpen) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private fun initTransitionManager() {
        val transitionFade = Fade()
        transitionFade.duration = 1500
        val transitionSlide = Slide(Gravity.END)
        transitionSlide.duration = 1500
        val transitionChangeBounds = ChangeBounds()
        transitionChangeBounds.duration = 2500
        val transitionSet = TransitionSet()
        transitionSet
            .addTransition(transitionFade)
            .addTransition(transitionChangeBounds)
            .addTransition(transitionSlide)
        TransitionManager.beginDelayedTransition(binding.transitionsContainer, transitionSet)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimationsFragment()
    }

}
