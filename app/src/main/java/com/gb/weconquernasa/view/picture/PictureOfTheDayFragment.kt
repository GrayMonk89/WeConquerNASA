package com.gb.weconquernasa.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentPictureOfTheDayBinding
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ONE
import com.gb.weconquernasa.utils.DEFAULT_VALUE_TWO
import com.gb.weconquernasa.utils.DEFAULT_VALUE_ZERO
import com.gb.weconquernasa.utils.LOG_KEY
import com.gb.weconquernasa.viewmodel.PictureOfTheDayAppState
import com.gb.weconquernasa.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    var isOpen: Boolean = false

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initEndIconListener()
        initSheetBehavior()
        initTabsListener()

        initImageViewListener()

    }

    private fun initImageViewListener() {
        binding.imageView.setOnClickListener {
            isOpen = !isOpen

            initTransitionManager()

            binding.imageView.scaleType = if (isOpen) {
                ImageView.ScaleType.CENTER_CROP
            } else {
                ImageView.ScaleType.CENTER_INSIDE
            }

            val params = (binding.imageView.layoutParams as CoordinatorLayout.LayoutParams)
            params.height = if (isOpen) {
                CoordinatorLayout.LayoutParams.MATCH_PARENT
            } else {
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            }
            binding.imageView.layoutParams = params
        }
    }

    private fun initTransitionManager() {
        val transitionChangeBounds = ChangeBounds().setDuration(1500)
        val transitionImage = ChangeImageTransform().setDuration(1500)

        val transitionSet = TransitionSet()
            .addTransition(transitionChangeBounds)
            .addTransition(transitionImage)

        TransitionManager.beginDelayedTransition(binding.root, transitionSet)
    }

    private fun initTabsListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        DEFAULT_VALUE_ZERO -> {
                            viewModel.getPicture(makeDate(DEFAULT_VALUE_ZERO))
                        }
                        DEFAULT_VALUE_ONE -> {
                            viewModel.getPicture(makeDate(DEFAULT_VALUE_ONE))
                        }
                        DEFAULT_VALUE_TWO -> {
                            viewModel.getPicture(makeDate(DEFAULT_VALUE_TWO))
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }


    private fun initSheetBehavior() {
        val bottomSheetBehavior =
            BottomSheetBehavior.from(binding.lifeHackBehavior.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d(LOG_KEY, "$slideOffset")
            }

        })
    }

    private fun initViewModel() {
        viewModel.getLiveData()
            .observe(viewLifecycleOwner, object : Observer<PictureOfTheDayAppState> {
                override fun onChanged(appState: PictureOfTheDayAppState) {
                    renderData(appState)
                }
            })
        viewModel.getPicture(makeDate(DEFAULT_VALUE_ZERO))
    }

    private fun initEndIconListener() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun makeDate(minus: Int): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -minus)
        return dateFormat.format(cal.time)
    }

    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Error -> {}
            is PictureOfTheDayAppState.Loading -> {}
            is PictureOfTheDayAppState.Success -> {
                binding.imageView.load(pictureOfTheDayAppState.pictureOfTheDayResponseData.url) {
                    crossfade(true)
                    placeholder(R.drawable.load)
                }
                binding.lifeHackBehavior.title.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.title
                binding.lifeHackBehavior.explanation.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.explanation
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()

    }
}