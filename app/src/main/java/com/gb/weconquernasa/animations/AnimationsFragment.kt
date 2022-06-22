package com.gb.weconquernasa.animations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {

    private val duration: Long = 2000
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

        binding.anim.fabCenter.setOnClickListener {

            initTransitionManager()

            isOpen = !isOpen
            if (!isOpen) {
                initConstraintSet(requireContext(), R.layout.include_fab_animations_start).applyTo(
                    binding.anim.container
                )
            } else {
                initConstraintSet(requireContext(), R.layout.include_fab_animations_end).applyTo(
                    binding.anim.container
                )
            }
        }
    }

    private fun initTransitionManager() {
        val transitionSet = TransitionSet()
        val transitionCB = ChangeBounds()
        val transitionFade = Fade()
        //val transitionSlide = Slide()
        transitionSet.addTransition(transitionCB)
        transitionSet.addTransition(transitionFade)
        //transitionSet.addTransition(transitionSlide)
        transitionSet.interpolator = AnticipateOvershootInterpolator(5f)
        transitionSet.duration = 1000
        TransitionManager.beginDelayedTransition(binding.anim.container, transitionSet)
    }

    private fun initConstraintSet(context: Context, constraintLayoutId: Int): ConstraintSet {
        val constraintSetStart = ConstraintSet()
        constraintSetStart.clone(context, constraintLayoutId)
        return constraintSetStart

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
