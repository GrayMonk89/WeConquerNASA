package com.gb.weconquernasa.animations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentAnimationsBonusStartBinding


class AnimationsFragment : Fragment() {

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


        binding.backgroundImage.setOnClickListener {
            val constraintSet = ConstraintSet()//.clone(binding.constraintContainer)

            TransitionManager.beginDelayedTransition(binding.constraintContainer, ChangeBounds())
            constraintSet.clone(requireContext(), R.layout.fragment_animations_bonus_end)
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
