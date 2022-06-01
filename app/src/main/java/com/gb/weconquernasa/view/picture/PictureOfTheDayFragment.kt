package com.gb.weconquernasa.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gb.weconquernasa.MainActivity
import com.gb.weconquernasa.R
import com.gb.weconquernasa.databinding.FragmentPictureOfTheDayBinding
import com.gb.weconquernasa.utils.LOG_KEY
import com.gb.weconquernasa.utils.showSnackBar
import com.gb.weconquernasa.viewmodel.PictureOfTheDayAppState
import com.gb.weconquernasa.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class PictureOfTheDayFragment : Fragment() {

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
                view?.showSnackBar(item.title.toString(),"",{},Snackbar.LENGTH_SHORT)
            }
            R.id.actionSettingsAppBar -> {
                view?.showSnackBar(item.title.toString(),"",{},Snackbar.LENGTH_SHORT)
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
        viewModel.getPicture()
    }

    private fun initEndIconListener() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Error -> {}
            is PictureOfTheDayAppState.Loading -> {}
            is PictureOfTheDayAppState.Success -> {
                binding.imageView.load(pictureOfTheDayAppState.pictureOfTheDayResponseData.url) {
                    crossfade(true)
                    placeholder(R.drawable.g2)
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