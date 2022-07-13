package com.gb.weconquernasa.view.ux

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.gb.weconquernasa.databinding.FragmentUxButtonBinding
import com.gb.weconquernasa.utils.KEY_CURRENT_THEME
import com.gb.weconquernasa.utils.KEY_SP
import com.gb.weconquernasa.utils.KEY_TUTORIAL
import com.gb.weconquernasa.utils.showSnackBar
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener

class ButtonUXFragment : Fragment() {

    private var _binding: FragmentUxButtonBinding? = null
    private val binding: FragmentUxButtonBinding
        get() = _binding!!

    companion object {
        fun newInstance() = ButtonUXFragment()
    }

    private fun setTutorialFlagShow(tutorialFlagShow: Boolean) {

        requireActivity().getSharedPreferences(KEY_TUTORIAL, Context.MODE_PRIVATE).edit()
            .putBoolean(KEY_TUTORIAL, tutorialFlagShow).apply()
    }

    private fun getTutorialFlagShow(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences(KEY_TUTORIAL, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_TUTORIAL, true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUxButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tutorialOn.setOnClickListener {
            setTutorialFlagShow(true)
        }

        if (getTutorialFlagShow()) {
            GuideView.Builder(requireContext())
                .setTitle("Warning!!!")
                .setGravity(Gravity.center)
                .setContentText("Don't push\n that damn \nBIG \nRED \n BUTTON")
                .setTargetView(binding.bigRedButton)
                .setGuideListener {
                    it.showSnackBar(
                        "Tutorial close.",
                        "Don`t show tutorial again",
                        { setTutorialFlagShow(false) })
                }
                .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
                .build()
                .show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}