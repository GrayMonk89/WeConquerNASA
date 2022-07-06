package com.gb.weconquernasa.view.picture

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
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
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
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
                //Log.d(LOG_KEY, "$slideOffset")
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
                binding.lifeHackBehavior.title.typeface =
                    Typeface.createFromAsset(requireActivity().assets, "myfont/SyakeDemo.ttf")
                //binding.lifeHackBehavior.title.textSize = 25F
                //binding.lifeHackBehavior.explanation.textSize = 25f
                binding.lifeHackBehavior.title.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.title
                binding.lifeHackBehavior.explanation.text =
                    pictureOfTheDayAppState.pictureOfTheDayResponseData.explanation

                workWithSpan()

            }
        }
    }

    private fun workWithSpan() {

        val textTitle = binding.lifeHackBehavior.title.text
        binding.lifeHackBehavior.explanation.text
        val textSpannable = "Most brilliant text \nparagraph one \nparagraph two \nparagraph three \nparagraph three \nparagraph three \nparagraph three \nparagraph three"
        val largeText = getText(R.string.large_text)
        val lengthList = lengthList(largeText.toString())

        //Log.d(LOG_KEY, lengthList(textSpannable).toString())



        val spannedString: SpannedString
        val spannableTitle = SpannableString(binding.lifeHackBehavior.title.text)
        val spannableString: SpannableString = SpannableString(binding.lifeHackBehavior.explanation.text)
        val spannableStringBuilder: SpannableStringBuilder = SpannableStringBuilder(binding.lifeHackBehavior.explanation.text)

        spannableTitle.setSpan(RelativeSizeSpan(2f),0,spannableTitle.length, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableStringBuilder.setSpan(RelativeSizeSpan(2f),0,spannableStringBuilder.length, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
        setForegroundColorSpanFromTo(spannableStringBuilder,R.color.my_color_2,9,25,SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
        setLineBackgroundSpan(spannableStringBuilder,R.color.my_color_5,0,5,0)
        setLineBackgroundSpan(spannableStringBuilder,R.color.my_color_5,60,70,0)
        setLineBackgroundSpan(spannableStringBuilder,R.color.my_color_5,160,170,0)
        setSuperscriptSpan(spannableStringBuilder, 45, 50)
        setSubscriptSpan(spannableStringBuilder, 55, 60)
        spannableStringBuilder.insert(19,"NEW")

        setURLSpan(spannableStringBuilder,"https://www.google.com", 74, 83, 0)


        binding.lifeHackBehavior.explanation.movementMethod = LinkMovementMethod.getInstance()

        spannedString = SpannedString(spannableStringBuilder)
        binding.lifeHackBehavior.title.text = spannableTitle
        binding.lifeHackBehavior.explanation.text = spannedString
    }

    private fun setURLSpan(spannableStringBuilder: SpannableStringBuilder,url: String, from: Int, to: Int, flag: Int = 0){
        spannableStringBuilder.setSpan(URLSpan(url),from,to,flag)
    }

    private fun setSuperscriptSpan(spannableStringBuilder: SpannableStringBuilder, from: Int, to: Int, flag: Int = 0){
        spannableStringBuilder.setSpan(SuperscriptSpan(), from, to, flag)
    }

    private fun setSubscriptSpan(spannableStringBuilder: SpannableStringBuilder, from: Int, to: Int, flag: Int = 0){
        spannableStringBuilder.setSpan(SubscriptSpan(), from, to, flag)
    }

    private fun setLineBackgroundSpan(spannableStringBuilder: SpannableStringBuilder, color: Int, from: Int, to: Int, flag: Int = 0){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            spannableStringBuilder.setSpan(
                LineBackgroundSpan.Standard(ContextCompat.getColor(requireContext(), color)),
                from,
                to, flag
            )
        }
    }

    private fun lengthList(s: String): List<Int> {
        val splitS: MutableList<String> = s.split("\n") as MutableList<String>
        val lengthList = mutableListOf<Int>()
        repeat(splitS.size) {if (it > 0)
            splitS[it] = "\n${splitS[it]}"}
        repeat(splitS.size) {
            if(it == 0){
                lengthList.add(splitS[it].length)
            } else if (it < splitS.size){
                lengthList.add(lengthList[it-1] + splitS[it].length)
            }
        }

        return lengthList
    }

    private fun setBulletSpan(lengthList: List<Int>, spannableString: SpannableString){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            for(i in lengthList.indices){
                if(i<lengthList.size-1) {
                    spannableString.setSpan(
                        BulletSpan(
                            20,
                            ContextCompat.getColor(requireContext(), R.color.my_color_5),
                            10
                        ),
                        lengthList[i] + 1,
                        lengthList[i + 1],
                        SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }

        }
    }

    private fun setForegroundColorSpanAuto(lengthList: List<Int>, spannableString: SpannableString){
        for(i in lengthList.indices){
            if(i<lengthList.lastIndex) {
                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red_700)),
                    lengthList[i] + 1,
                    lengthList[i + 1], SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    private fun setForegroundColorSpanFromTo(spannableStringBuilder: SpannableStringBuilder,color: Int, from: Int, to: Int, flag: Int){
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), color)),
            from,
            to, flag
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfTheDayFragment()

    }
}