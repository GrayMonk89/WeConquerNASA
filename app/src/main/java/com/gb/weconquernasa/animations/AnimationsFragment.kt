package com.gb.weconquernasa.animations

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.gb.weconquernasa.databinding.FragmentAnimationsBinding


class AnimationsFragment : Fragment() {

    private var isOpen: Boolean = false

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

        initMotionButtonListener()
    }

    private fun initMotionButtonListener() {
        binding.buttonMotion.setOnClickListener {
            isOpen = !isOpen

            val params = (binding.buttonMotion.layoutParams as FrameLayout.LayoutParams)

            params.gravity = if (isOpen) {
                setPath(90f, 4000)
                Gravity.CENTER or Gravity.END
            } else {
                setPath(10f, 1500)
                Gravity.TOP or Gravity.START
            }
            binding.buttonMotion.layoutParams = params
        }
    }

    private fun setPath(maximumAngle: Float, duration: Long) {
        val transition = ChangeBounds()
        val path = ArcMotion()
        path.maximumAngle = maximumAngle
        transition.setPathMotion(path)
        transition.duration = duration
        TransitionManager.beginDelayedTransition(binding.root, transition)
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
