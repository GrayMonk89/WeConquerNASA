package com.gb.weconquernasa.animations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentAnimationsBonusStartBinding


class AnimationsFragment : Fragment() {

    private val duration: Long = 1000
    var isOpen: Boolean = false

    private var _binding: FragmentAnimationsBonusStartBinding? = null
    private val binding: FragmentAnimationsBonusStartBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationsBonusStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initShowPhotoDetails()
    }

    private fun initShowPhotoDetails() {
        binding.backgroundImage.setOnClickListener {

            val constraintSet = ConstraintSet()
            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(20f)
            transition.duration = duration
            TransitionManager.beginDelayedTransition(binding.constraintContainer,transition)
            isOpen = !isOpen
            if (isOpen) {
                constraintSet.clone(requireContext(), R.layout.fragment_animations_bonus_end)
            } else {
                constraintSet.clone(requireContext(), R.layout.fragment_animations_bonus_start)
            }
            constraintSet.applyTo(binding.constraintContainer)
        }
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
