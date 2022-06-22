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

    private val duration: Long = 2000
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
            constraintSet.clone(binding.constraintContainer)

            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(5f)
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(binding.constraintContainer,transition)

            isOpen = !isOpen
            if(isOpen){
                constraintSet.clear(R.id.description, ConstraintSet.BOTTOM)
                constraintSet.clear(R.id.description, ConstraintSet.TOP)
                constraintSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.END)
                constraintSet.connect(R.id.description,ConstraintSet.BOTTOM,R.id.backgroundImage,ConstraintSet.BOTTOM)
            }else{
                constraintSet.clear(R.id.description, ConstraintSet.BOTTOM)
                constraintSet.clear(R.id.description, ConstraintSet.TOP)
                constraintSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.START)
                constraintSet.connect(R.id.description,ConstraintSet.TOP,R.id.backgroundImage,ConstraintSet.BOTTOM)
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
