package com.gb.weconquernasa.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentPictureOfTheDayBinding
import com.gb.weconquernasa.utils.*
import com.gb.weconquernasa.view.settings.SettingsFragment
import com.gb.weconquernasa.viewmodel.PictureOfTheDayAppState
import com.gb.weconquernasa.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class PictureOfTheDayFragment : Fragment() {

    var isMain = true

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
        initMenuAppBar()
        initFABListener()
        initTabsListener()


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

    private fun initFABListener() {
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_empty)
            } else {
                binding.bottomAppBar.navigationIcon = (ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                ))
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }

    private fun initMenuAppBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionFavouriteAppBar -> {
                view?.showSnackBar(item.title.toString(), "", {}, Snackbar.LENGTH_SHORT)
            }
            R.id.actionSettingsAppBar -> {
                view?.showSnackBar(item.title.toString(), "", {}, Snackbar.LENGTH_SHORT)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            R.id.actionExitAppBar -> {
                exitProcess(0)
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }

        }
        return super.onOptionsItemSelected(item)
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